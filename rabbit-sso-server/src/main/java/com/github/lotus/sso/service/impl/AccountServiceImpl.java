package com.github.lotus.sso.service.impl;

import com.github.lotus.chaos.api.SmsServiceApi;
import com.github.lotus.common.utils.Rules;
import com.github.lotus.sso.mapstruct.AccountMapping;
import com.github.lotus.sso.pojo.ro.ConfirmQrcodeRo;
import com.github.lotus.sso.pojo.ro.JoinAccountRo;
import com.github.lotus.sso.pojo.ro.LoginRo;
import com.github.lotus.sso.service.AccountService;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.GetLoginQrcodeVo;
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

import java.util.Objects;
import java.util.Optional;

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
        Optional<String> resultOpt = Rules.create()
            .rule(JoinAccountRo.Mode.UsePhone, Rules.Supplier(() -> this.joinUsePhone(ro.getPhoneMode())))
            .rule(JoinAccountRo.Mode.UseUsername, Rules.Supplier(() -> this.joinUseUsername(ro.getUsernameMode())))
            .rule(JoinAccountRo.Mode.UseEmail, Rules.Supplier(() -> this.joinUseEmail(ro.getEmailMode())))
            .of(ICode.ofThrow(ro.getMode(), JoinAccountRo.Mode.class));
        return resultOpt.orElseThrow(() -> ServiceException.wrap("该注册方式暂不支持"));
    }

    @Override
    public GetLoginQrcodeVo getLoginQrcode() {
        return userServiceApi.getLoginQrcode();
    }

    @Override
    public void confirmQrcode(ConfirmQrcodeRo ro) {
        userServiceApi.confirmQrcode(ro.getIdFlag(), ro.getUserId());
    }

    @Override
    public String login(LoginRo ro) {
        Optional<String> resultOpt = Rules.create()
            .rule(LoginRo.Mode.UseSms, Rules.Supplier(() -> this.loginUseSms(ro.getSmsMode())))
            .rule(LoginRo.Mode.UsePassword, Rules.Supplier(() -> this.loginUsePassword(ro.getPasswordMode())))
            .rule(LoginRo.Mode.UseQrcode, Rules.Supplier(() -> this.loginUseQrcode(ro.getQrcodeMode())))
            .of(ICode.ofThrow(ro.getMode(), LoginRo.Mode.class));
        return resultOpt.orElseThrow(() -> ServiceException.wrap("该登录方式暂不支持"));
    }

    private String loginUseQrcode(LoginRo.QrcodeMode ro) {
        ValidatorUtils.validThrow(ro);
        UserDetailVo userDetail = userServiceApi.getUserByIdFlag(ro.getIdFlag());
        if (Objects.isNull(userDetail)) {
            return null;
        }
        return userServiceApi.getUserToken(userDetail.getUsername());
    }

    private String joinUseEmail(JoinAccountRo.EmailMode ro) {
        ValidatorUtils.validThrow(ro, JoinAccountRo.EmailModeGroup.class);
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
        ValidatorUtils.validThrow(ro, JoinAccountRo.UsernameModeGroup.class);
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
        ValidatorUtils.validThrow(ro, JoinAccountRo.PhoneModeGroup.class);
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

    private String loginUseSms(LoginRo.SmsMode ro) {
        ValidatorUtils.validThrow(ro, LoginRo.SmsModeGroup.class);
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
        ValidatorUtils.validThrow(ro, LoginRo.PasswordModeGroup.class);
        String username = ro.getUsername();
        String password = ro.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            log.warn("登陆失败", e);
            throw ServiceException.wrap("{}", e.getMessage());
        }
        return userServiceApi.getUserToken(username);
    }

}
