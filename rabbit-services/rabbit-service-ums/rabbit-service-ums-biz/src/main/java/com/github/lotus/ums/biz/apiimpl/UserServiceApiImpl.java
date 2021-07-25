package com.github.lotus.ums.biz.apiimpl;

import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.GetLoginQrcodeVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserServiceApiImpl implements UserServiceApi {
    private final UserService service;

    @Override
    public UserDetailVo getUserByUsername(String username) {
        return service.getUserDetailVoByUsername(username);
    }

    @Override
    public UserDetailVo getUserByUsernameOrEmailOrPhone(String unique) {
        return service.getUserDetailVoByUsernameOrEmailOrPhone(unique);
    }

    @Override
    public UserDetailVo getUserByPhone(String phone) {
        return service.getUserByPhone(phone);
    }

    @Override
    public UserDetailVo createAccount(CreateAccountRo ro) {
        return service.createAccount(ro);
    }

    @Override
    public String getUserToken(String username) {
        return service.getToken(username);
    }

    @Override
    public String getUsername(String token) {
        return service.getUsername(token);
    }

    @Override
    public List<AccountVo> listAccountVoById(List<Long> id) {
        return service.listAccountVoById(id);
    }

    @Override
    public AccountVo getById(Long id) {
        return service.getAccountVoById(id);
    }

    @Override
    public UserDetailVo getUserDetailVoByUsername(String username) {
        return service.getUserDetailVoByUsername(username);
    }

    @Override
    public UserDetailVo getUserByIdFlag(String idFlag) {
        return service.getUserByIdFlag(idFlag);
    }

    @Override
    public GetLoginQrcodeVo getLoginQrcode() {
        return service.getLoginQrcode();
    }

    @Override
    public void confirmQrcode(String idFlag, Long userId) {
        service.confirmQrcode(idFlag, userId);
    }
}
