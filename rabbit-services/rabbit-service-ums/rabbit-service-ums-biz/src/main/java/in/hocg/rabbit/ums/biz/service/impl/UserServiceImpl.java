package in.hocg.rabbit.ums.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.rabbit.chaos.api.EmailServiceApi;
import in.hocg.rabbit.chaos.api.SmsServiceApi;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.ProjectServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.common.utils.JwtUtils;
import in.hocg.rabbit.common.utils.RabbitUtils;
import in.hocg.rabbit.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.rabbit.ums.api.pojo.ro.ForgotRo;
import in.hocg.rabbit.ums.api.pojo.ro.InsertSocialRo;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.ums.api.pojo.vo.GetLoginQrcodeVo;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.rabbit.ums.biz.cache.UmsCacheService;
import in.hocg.rabbit.ums.biz.entity.Social;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.mapper.UserMapper;
import in.hocg.rabbit.ums.biz.mapstruct.UserMapping;
import in.hocg.rabbit.ums.biz.pojo.ro.*;
import in.hocg.rabbit.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserCompleteVo;
import in.hocg.rabbit.ums.biz.service.AuthorityService;
import in.hocg.rabbit.ums.biz.service.RoleService;
import in.hocg.rabbit.ums.biz.service.RoleUserRefService;
import in.hocg.rabbit.ums.biz.service.SocialService;
import in.hocg.rabbit.ums.biz.service.UserService;
import com.google.common.collect.Maps;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.oss.autoconfigure.core.OssFileBervice;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * [用户模块] 账号表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl extends AbstractServiceImpl<UserMapper, User>
    implements UserService {
    private final UserMapping mapping;
    private final SocialService socialService;
    private final SmsServiceApi smsServiceApi;
    private final EmailServiceApi emailServiceApi;
    private final FileServiceApi fileServiceApi;
    private final ProjectServiceApi projectServiceApi;
    private final AuthorityService authorityService;
    private final RoleService roleService;
    private final RoleUserRefService roleUserRefService;
    private final UmsCacheService cacheService;
    private final OssFileBervice ossFileService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo createAccount(CreateAccountRo ro) {
        String username = ro.getUsername();
        String nickname = ro.getNickname();
        String avatar = ro.getAvatar();
        List<CreateAccountRo.SocialItem> socials = ro.getSocials();
        boolean isUseDefaultUsername = StrUtil.isBlank(username);
        boolean isUseDefaultNickname = StrUtil.isBlank(nickname);
        nickname = LangUtils.getOrDefault(nickname, "暂未设置");
        username = LangUtils.getOrDefault(username, RandomUtil.randomString(18));

        String phone = ro.getPhone();
        String email = ro.getEmail();
        String password = LangUtils.getOrDefault(ro.getPassword(), IdUtil.fastSimpleUUID());
        String createdIp = ro.getCreatedIp();

        LocalDateTime createdAt = LocalDateTime.now();

        User entity = new User()
            .setNickname(nickname)
            .setUsername(username)
            .setPhone(phone)
            .setEmail(email)
            .setAvatarUrl(avatar)
            .setPassword(password)
            .setCreatedIp(createdIp)
            .setCreatedAt(createdAt);
        ValidUtils.isTrue(validInsert(entity), "系统繁忙，注册失败");
        final Long entityId = entity.getId();

        // 关联社交账号，如果有的话
        if (CollectionUtil.isNotEmpty(socials)) {
            for (CreateAccountRo.SocialItem item : socials) {
                socialService.insertOne(new InsertSocialRo()
                    .setSocialType(item.getSocialType())
                    .setSocialId(item.getSocialId())
                    .setUserId(entityId));
            }
        }

        final User update = new User().setId(entityId);

        // 设置默认头像，如果没有指定头像的话
        if (Strings.isBlank(avatar)) {
            update.setAvatarUrl(fileServiceApi.getAvatarUrl(entityId));
        }

        // 设置默认账号，如果没有指定账号的话
        if (isUseDefaultUsername) {
            username = RabbitUtils.getDefaultUsername(entityId);
            update.setUsername(username);
        }

        boolean isOk = this.validUpdateById(update);
        ValidUtils.isTrue(isOk, "系统繁忙，注册失败");
        return getUserDetailVoByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUserDetailVoByUsername(String username) {
        return getByUsername(username).map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUserDetailVoByUsernameOrEmailOrPhone(String unique) {
        return getByUsernameOrEmailOrPhone(unique)
            .map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<User> getByUsernameOrEmailOrPhone(String unique) {
        return lambdaQuery()
            .or().eq(User::getUsername, unique)
            .or().eq(User::getEmail, unique)
            .or().eq(User::getPhone, unique).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> listAccountById(List<Long> values) {
        if (CollectionUtil.isEmpty(values)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(User::getId, values).list();
    }

    @Override
    public String getToken(String username) {
        return JwtUtils.encode(username);
    }

    @Override
    public String getUsername(String token) {
        return JwtUtils.decode(token);
    }

    @Override
    public UserDetailVo getUserByPhone(String phone) {
        return getAccountByPhone(phone)
            .map(mapping::asUserDetailVo)
            .orElse(null);
    }

    @Override
    public List<AccountVo> listAccountVoById(List<Long> id) {
        return LangUtils.toList(this.listAccountById(id), mapping::asAccountVo);
    }

    @Override
    public AccountVo getAccountVoById(Long userId) {
        User entity = getById(userId);
        return mapping.asAccountVo(entity);
    }

    @Override
    public AccountComplexVo getComplexById(Long userId) {
        User entity = baseMapper.selectById(userId);
        return this.convert(entity);
    }

    @Override
    public Long updateAccount(Long userId, UpdateAccountRo ro) {
        Long updaterId = ro.getUpdaterId();
        User entity = mapping.asAccount(ro);
        entity.setId(userId);
        entity.setLastUpdatedAt(LocalDateTime.now());
        entity.setLastUpdater(updaterId);
        validUpdateById(entity);
        return userId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updatePhone(UpdateAccountPhoneRo ro) {
        Long id = ro.getId();
        String phone = ro.getPhone();
        String verifyCode = ro.getVerifyCode();
        Long updaterId = ro.getUpdaterId();
        LocalDateTime now = LocalDateTime.now();

        if (!smsServiceApi.validVerifyCode(phone, verifyCode)) {
            ValidUtils.fail("验证码错误");
        }

        User updated = new User()
            .setId(id)
            .setPhone(phone)
            .setLastUpdater(updaterId)
            .setLastUpdatedAt(now);
        ValidUtils.isTrue(validUpdateById(updated), "操作失败");
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateEmail(UpdateAccountEmailRo ro) {
        Long id = ro.getId();
        String email = ro.getEmail();
        String verifyCode = ro.getVerifyCode();
        Long updaterId = ro.getUpdaterId();
        LocalDateTime now = LocalDateTime.now();

        if (!emailServiceApi.validVerifyCode(email, verifyCode)) {
            ValidUtils.fail("验证码错误");
        }

        User updated = new User()
            .setId(id)
            .setEmail(email)
            .setLastUpdater(updaterId)
            .setLastUpdatedAt(now);
        ValidUtils.isTrue(validUpdateById(updated), "操作失败");
        return id;
    }

    @Override
    public void validEntity(User entity) {
        super.validEntity(entity);
        Long id = entity.getId();
        String phone = entity.getPhone();
        String username = entity.getUsername();
        String email = entity.getEmail();
        boolean isInsert = Objects.isNull(id);

        if (Objects.nonNull(username)) {
            Assert.isFalse(hasUsername(username, id), "该用户名已被注册");
        }
        if (Objects.nonNull(phone)) {
            Assert.isFalse(hasPhone(phone, id), "该手机号已被注册");
        }
        if (Objects.nonNull(email)) {
            Assert.isFalse(hasEmail(email, id), "该邮箱已被注册");
        }
    }

    private AccountComplexVo convert(User entity) {
        Long userId = entity.getId();

        AccountComplexVo result = mapping.asComplex(entity);

        // 已绑定的社交方式
        List<Social> socials = socialService.listSocialByUserId(userId);
        result.setSocial(socials.parallelStream().map(social ->
            new AccountComplexVo.SocialItem().setSocialType(social.getSocialType())
        ).collect(Collectors.toList()));

        result.setRoles(roleService.listOrdinaryByUserId(userId));
        return result;
    }

    private boolean hasUsername(String username, Long... ignoreId) {
        return has(User::getUsername, username, User::getId, ignoreId);
    }

    private boolean hasPhone(String phone, Long... ignoreId) {
        return has(User::getPhone, phone, User::getId, ignoreId);
    }

    private boolean hasEmail(String email, Long... ignoreId) {
        return has(User::getEmail, email, User::getId, ignoreId);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AuthorityTreeNodeVo> listTreeCurrentAuthority(String projectSn, Long userId) {
        Long projectId = null;
        if (Strings.isNotBlank(projectSn)) {
            ProjectComplexVo project = projectServiceApi.getComplexByProjectSn(projectSn);
            if (Objects.isNull(project)) {
                return Collections.emptyList();
            }
            projectId = project.getId();
        }
        return authorityService.listByProjectIdAndUserId(projectId, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> listCurrentAuthorityCode(String projectSn, Long userId) {
        Long projectId = null;
        if (Strings.isNotBlank(projectSn)) {
            ProjectComplexVo project = projectServiceApi.getComplexByProjectSn(projectSn);
            if (Objects.isNull(project)) {
                return Collections.emptyList();
            }
            projectId = project.getId();
        }
        return authorityService.listAuthorityCodeByProjectIdAndUserId(projectId, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<AccountComplexVo> paging(UserPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convert);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserCompleteVo> complete(UserCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(this::convertComplete).getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantRole(Long userId, RoleGrantUserRo ro) {
        roleUserRefService.grantRole(userId, ro.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountComplexVo getComplex(Long id) {
        return this.convert(getById(id));
    }

    @Override
    public UserDetailVo getUserByIdFlag(String idFlag) {
        String username = cacheService.getQrcodeLoginKey(idFlag);
        return this.getUserDetailVoByUsername(username);
    }

    @Override
    @SneakyThrows(IOException.class)
    public GetLoginQrcodeVo getLoginQrcode() {
        String idFlag = (IdUtil.simpleUUID() + RandomUtil.randomString(5)).toUpperCase();
        cacheService.applyQrcodeLoginKey(idFlag);
        HashMap<String, String> params = Maps.newHashMap();
        params.put("idFlag", idFlag);
        BufferedImage image = QrCodeUtil.generate(JSON.toJSONString(params), 400, 400);
        File output = Files.createTempFile("tmp", ".png").toFile();
        ImageIO.write(image, "png", output);
        String fileUrl = ossFileService.upload(output);
        return new GetLoginQrcodeVo()
            .setQrCodeUrl(fileUrl)
            .setIdFlag(idFlag);
    }

    @Override
    public void confirmQrcode(String idFlag, Long userId) {
        User user = Assert.notNull(getById(userId), "用户信息错误");
        cacheService.updateQrcodeLoginKey(idFlag, user.getUsername());
    }

    @Override
    public void forgot(ForgotRo ro) {
        ForgotRo.Mode mode = ICode.ofThrow(ro.getMode(), ForgotRo.Mode.class);
        if (ForgotRo.Mode.UseEmail.equals(mode)) {
            forgotEmail(Assert.notNull(ro.getEmailMode()));
        } else if (ForgotRo.Mode.UsePhone.equals(mode)) {
            forgotPhone(Assert.notNull(ro.getPhoneMode()));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void register(RegisterRo ro) {
        Assert.isTrue(smsServiceApi.validVerifyCode(ro.getPhone(), ro.getCode()), "验证码错误");
        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        this.createAccount(createAccountRo);
    }

    private void forgotEmail(@Validated ForgotRo.EmailMode ro) {
        // todo 发送邮件，重置密码的链接
        throw new UnsupportedOperationException();
    }

    private void forgotPhone(@Validated ForgotRo.PhoneMode ro) {
        String phone = ro.getPhone();
        String password = ro.getPassword();
        Assert.isTrue(smsServiceApi.validVerifyCode(phone, ro.getCode()), "验证码错误");
        User entity = getAccountByPhone(phone).orElseThrow(() -> ServiceException.wrap("手机号码不存在"));
        updatePasswordById(entity.getId(), password);
    }

    private void updatePasswordById(Long id, String password) {
        User update = new User();
        update.setId(id);
        update.setPassword(passwordEncoder.encode(password));
        validEntity(update);
    }

    private Optional<User> getAccountByPhone(String phone) {
        return lambdaQuery().eq(User::getPhone, phone).oneOpt();
    }

    private UserCompleteVo convertComplete(User entity) {
        return mapping.asUserCompleteVo(entity);
    }
}
