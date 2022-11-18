package in.hocg.rabbit.ums.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.oss.autoconfigure.core.OssFileBervice;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.boot.validation.core.ValidatorUtils;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.rabbit.chaos.api.ChaosServiceApi;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeDeviceType;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.ProjectServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.common.utils.JwtUtils;
import in.hocg.rabbit.common.utils.RabbitUtils;
import in.hocg.rabbit.common.utils.Rules;
import in.hocg.rabbit.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.rabbit.ums.api.pojo.ro.ForgotRo;
import in.hocg.rabbit.ums.api.pojo.ro.InsertSocialRo;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.ums.api.pojo.vo.GetLoginQrcodeVo;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.rabbit.ums.biz.cache.UmsCacheService;
import in.hocg.rabbit.ums.biz.entity.Role;
import in.hocg.rabbit.ums.biz.entity.Social;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.mapper.UserMapper;
import in.hocg.rabbit.ums.biz.mapstruct.UserMapping;
import in.hocg.rabbit.ums.biz.pojo.ro.JoinAccountRo;
import in.hocg.rabbit.ums.biz.pojo.ro.RoleGrantUserRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UpdateAccountEmailRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UpdateAccountPhoneRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UpdateAccountRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UserCompleteRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UserPagingRo;
import in.hocg.rabbit.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserCompleteVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserInfoMeVo;
import in.hocg.rabbit.ums.biz.service.AuthorityService;
import in.hocg.rabbit.ums.biz.service.RoleService;
import in.hocg.rabbit.ums.biz.service.RoleUserRefService;
import in.hocg.rabbit.ums.biz.service.SocialService;
import in.hocg.rabbit.ums.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final ChaosServiceApi chaosServiceApi;
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
    public String renewToken(String token) {
        return JwtUtils.encode(JwtUtils.decode(token));
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
        String serialNo = ro.getSerialNo();
        String verifyCode = ro.getVerifyCode();
        Long updaterId = ro.getUpdaterId();
        LocalDateTime now = LocalDateTime.now();

        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(serialNo, verifyCode);
        String phone = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Phone);

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
        String serialNo = ro.getSerialNo();
        String verifyCode = ro.getVerifyCode();
        Long updaterId = ro.getUpdaterId();
        LocalDateTime now = LocalDateTime.now();

        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(serialNo, verifyCode);
        String email = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Email);

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
            forgotEmail(ro.getEmailMode());
        } else if (ForgotRo.Mode.UsePhone.equals(mode)) {
            forgotPhone(ro.getPhoneMode());
        }
        throw new UnsupportedOperationException(StrUtil.format("找回密码的方式[{}]暂不支持", ro.getMode()));
    }

    @Override
    public void register(RegisterRo ro) {
        String serialNo = ro.getSerialNo();
        String verifyCode = ro.getVerifyCode();

        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(serialNo, verifyCode);
        String phone = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Phone);

        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setPhone(phone);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        this.createAccount(createAccountRo);
    }

    @Override
    public UserInfoMeVo getMeUserInfoById(Long id) {
        return mapping.asUserInfoMeVo(getById(id));
    }

    @Override
    public String registerAfterLogin(JoinAccountRo ro) {
        Optional<String> resultOpt = Rules.create()
            .rule(JoinAccountRo.Mode.UsePhone, Rules.Supplier(() -> this.joinUsePhone(ro.getPhoneMode())))
            .rule(JoinAccountRo.Mode.UseUsername, Rules.Supplier(() -> this.joinUseUsername(ro.getUsernameMode())))
            .rule(JoinAccountRo.Mode.UseEmail, Rules.Supplier(() -> this.joinUseEmail(ro.getEmailMode())))
            .of(ICode.ofThrow(ro.getMode(), JoinAccountRo.Mode.class));
        return resultOpt.orElseThrow(() -> ServiceException.wrap("该注册方式暂不支持"));
    }

    @Override
    public List<String> getAuthorities(String username) {
        return getByUsername(username).map(User::getId).map(roleUserRefService::listByUserId).orElse(Collections.emptyList())
            .stream().map(Role::getEncoding).collect(Collectors.toList());
    }


    private String joinUseUsername(JoinAccountRo.Mode.UseUsernameRo ro) {
        ValidatorUtils.validThrow(Assert.notNull(ro, "账号模式参数错误"));
        String username = ro.getUsername();
        String password = ro.getPassword();

        CreateAccountRo newRo = new CreateAccountRo()
            .setPassword(passwordEncoder.encode(password))
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setUsername(username);
        UserDetailVo userDetailVo = this.createAccount(newRo);
        return this.getToken(userDetailVo.getUsername());
    }

    private String joinUsePhone(JoinAccountRo.Mode.UsePhoneRo ro) {
        ValidatorUtils.validThrow(Assert.notNull(ro, "手机号模式参数错误"));
        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(ro.getSerialNo(), ro.getVerifyCode());
        String phone = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Phone);
        CreateAccountRo newRo = new CreateAccountRo()
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setPhone(phone);
        UserDetailVo userDetailVo = this.createAccount(newRo);
        return this.getToken(userDetailVo.getUsername());
    }

    private String joinUseEmail(JoinAccountRo.Mode.UseEmailRo ro) {
        ValidatorUtils.validThrow(Assert.notNull(ro, "邮箱模式参数错误"));
        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(ro.getSerialNo(), ro.getVerifyCode());
        String email = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Email);
        String password = ro.getPassword();
        CreateAccountRo newRo = new CreateAccountRo()
            .setPassword(passwordEncoder.encode(password))
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setEmail(email);
        UserDetailVo userDetailVo = this.createAccount(newRo);
        return this.getToken(userDetailVo.getUsername());
    }


    private void forgotEmail(ForgotRo.Mode.UseEmailRo ro) {
        Assert.notNull(ro, "邮箱模式参数错误");
        String serialNo = ro.getSerialNo();
        String password = ro.getPassword();
        String verifyCode = ro.getVerifyCode();

        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(serialNo, verifyCode);
        String email = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Email);

        User entity = lambdaQuery().eq(User::getEmail, email).oneOpt().orElseThrow(() -> ServiceException.wrap("邮箱不存在"));
        updatePasswordById(entity.getId(), password);
    }

    private void forgotPhone(ForgotRo.Mode.UsePhoneRo ro) {
        Assert.notNull(ro, "手机号模式参数错误");
        String serialNo = ro.getSerialNo();
        String password = ro.getPassword();
        String verifyCode = ro.getVerifyCode();

        ValidVerifyCodeVo validResult = chaosServiceApi.validVerifyCode(serialNo, verifyCode);
        String phone = validResult.getDeviceNoThrow(VerifyCodeDeviceType.Phone);

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
