package com.github.lotus.ums.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.github.lotus.chaos.api.EmailServiceApi;
import com.github.lotus.chaos.api.SmsServiceApi;
import com.github.lotus.com.api.FileServiceApi;
import com.github.lotus.common.utils.JwtUtils;
import com.github.lotus.common.utils.RabbitUtils;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.ro.InsertSocialRo;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.Social;
import com.github.lotus.ums.biz.mapper.AccountMapper;
import com.github.lotus.ums.biz.mapstruct.AccountMapping;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountEmailRo;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountPhoneRo;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountRo;
import com.github.lotus.ums.biz.pojo.vo.AccountComplexVo;
import com.github.lotus.ums.biz.service.AccountService;
import com.github.lotus.ums.biz.service.SocialService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
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
public class AccountServiceImpl extends AbstractServiceImpl<AccountMapper, User>
    implements AccountService {
    private final AccountMapping mapping;
    private final SocialService socialService;
    private final SmsServiceApi smsServiceApi;
    private final EmailServiceApi emailServiceApi;
    private final FileServiceApi fileServiceApi;

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
            .setAvatar(avatar)
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
                    .setAccountId(entityId));
            }
        }

        final User update = new User()
            .setId(entityId);

        // 设置默认头像，如果没有指定头像的话
        if (Strings.isBlank(avatar)) {
            update.setAvatar(fileServiceApi.getAvatarUrl(entityId));
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
        return getByUsername(username)
            .map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUserDetailVoByUsernameOrEmailOrPhone(String unique) {
        return getAccountByUsernameOrEmailOrPhone(unique)
            .map(mapping::asUserDetailVo).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<User> getAccountByUsernameOrEmailOrPhone(String unique) {
        return lambdaQuery()
            .or().eq(User::getUsername, unique)
            .or().eq(User::getEmail, unique)
            .or().eq(User::getPhone, unique).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> listAccountByAccountId(List<Long> values) {
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
    public List<AccountVo> listAccountVoByAccountId(List<Long> id) {
        return LangUtils.toList(this.listAccountByAccountId(id), mapping::asAccountVo);
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
        Long entityId = entity.getId();

        AccountComplexVo result = mapping.asComplex(entity);

        // 已绑定的社交方式
        List<Social> socials = socialService.listSocialByAccountId(entityId);
        result.setSocial(socials.parallelStream().map(social ->
            new AccountComplexVo.SocialItem().setSocialType(social.getSocialType())
        ).collect(Collectors.toList()));

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

    private Optional<User> getAccountByPhone(String phone) {
        return lambdaQuery().eq(User::getPhone, phone).oneOpt();
    }
}
