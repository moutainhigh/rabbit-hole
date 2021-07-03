package com.github.lotus.sso.service.impl;

import com.github.lotus.chaos.api.SmsServiceApi;
import com.github.lotus.common.utils.Rules;
import com.github.lotus.sso.mapstruct.AccountMapping;
import com.github.lotus.sso.pojo.ro.JoinAccountRo;
import com.github.lotus.sso.pojo.ro.LoginRo;
import com.github.lotus.sso.service.AccountService;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.autoconfigure.core.ValidatorUtils;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountServiceImpl implements AccountService {
    private final AccountMapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SmsServiceApi smsServiceApi;
    private final UserServiceApi userServiceApi;

    @Override
    public String join(JoinAccountRo ro) {
        JoinAccountRo.Mode mode = ICode.ofThrow(ro.getMode(), JoinAccountRo.Mode.class);
        return ((String) Rules.create()
            .rule(JoinAccountRo.Mode.UsePhone, Rules.Supplier(() -> {
                ValidatorUtils.validThrow(ro, JoinAccountRo.PhoneModeGroup.class);
                return this.joinUsePhone(ro.getPhoneMode());
            }))
            .rule(JoinAccountRo.Mode.UseUsername, Rules.Supplier(() -> {
                ValidatorUtils.validThrow(ro, JoinAccountRo.UsernameModeGroup.class);
                return this.joinUseUsername(ro.getUsernameMode());
            }))
            .rule(JoinAccountRo.Mode.UseEmail, Rules.Supplier(() -> {
                ValidatorUtils.validThrow(ro, JoinAccountRo.EmailModeGroup.class);
                return this.joinUseEmail(ro.getEmailMode());
            })).of(mode).orElseThrow(() -> ServiceException.wrap("该注册方式暂不支持")));
    }

    private String joinUseEmail(JoinAccountRo.EmailMode ro) {
        String email = ro.getEmail();
        String verifyCode = ro.getVerifyCode();
        String password = ro.getPassword();

        // todo: 后续添加校验邮件验证码 verifyCode
        CreateAccountRo newRo = new CreateAccountRo()
            .setPassword(passwordEncoder.encode(password))
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setEmail(email);
        UserDetailVo userDetailVo = userServiceApi.createAccount(newRo);
        return userServiceApi.getUserToken(userDetailVo.getUsername());
    }

    private String joinUseUsername(JoinAccountRo.UsernameMode ro) {
        String username = ro.getUsername();
        String password = ro.getPassword();

        CreateAccountRo newRo = new CreateAccountRo()
            .setPassword(passwordEncoder.encode(password))
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setUsername(username);
        UserDetailVo userDetailVo = userServiceApi.createAccount(newRo);
        return userServiceApi.getUserToken(userDetailVo.getUsername());
    }

    private String joinUsePhone(JoinAccountRo.PhoneMode ro) {
        String phone = ro.getPhone();
        String verifyCode = ro.getVerifyCode();

        if (!smsServiceApi.validVerifyCode(phone, verifyCode)) {
            throw ServiceException.wrap("验证码错误");
        }
        CreateAccountRo newRo = new CreateAccountRo()
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setPhone(phone);
        UserDetailVo userDetailVo = userServiceApi.createAccount(newRo);
        return userServiceApi.getUserToken(userDetailVo.getUsername());
    }

    @Override
    public String login(LoginRo ro) {
        LoginRo.Mode mode = ICode.ofThrow(ro.getMode(), LoginRo.Mode.class);
        switch (mode) {
            case UseSms: {
                ValidatorUtils.validThrow(ro, LoginRo.SmsModeGroup.class);
                return this.loginUseSms(ro.getSmsMode());
            }
            case UsePassword: {
                ValidatorUtils.validThrow(ro, LoginRo.PasswordModeGroup.class);
                return this.loginUsePassword(ro.getPasswordMode());
            }
            default:
                throw ServiceException.wrap("该登录方式暂不支持");
        }
    }

    private String loginUseSms(LoginRo.SmsMode ro) {
        String phone = ro.getPhone();
        String verifyCode = ro.getVerifyCode();
        if (!smsServiceApi.validVerifyCode(phone, verifyCode)) {
            throw ServiceException.wrap("验证码错误");
        }
        UserDetailVo userDetail = userServiceApi.getUserByPhone(phone);
        ValidUtils.notNull(userDetail, "手机号码错误");
        return userServiceApi.getUserToken(userDetail.getUsername());
    }

    private String loginUsePassword(LoginRo.PasswordMode ro) {
        String username = ro.getUsername();
        String password = ro.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            log.warn("登陆失败", e);
            throw ServiceException.wrap("用户名或密码错误");
        }
        return userServiceApi.getUserToken(username);
    }

}
