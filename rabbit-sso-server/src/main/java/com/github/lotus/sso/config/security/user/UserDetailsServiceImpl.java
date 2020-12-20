package com.github.lotus.sso.config.security.user;

import cn.hutool.core.lang.Assert;
import com.github.lotus.chaos.api.modules.ums.AccountServiceApi;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by hocgin on 2020/1/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final AccountServiceApi accountApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailVo user = accountApi.getUserByUsernameOrEmailOrPhone(username);
        Assert.notNull(user, "账号或密码错误");
        return new User(username, user.getPassword(), Collections.emptyList());
    }

}
