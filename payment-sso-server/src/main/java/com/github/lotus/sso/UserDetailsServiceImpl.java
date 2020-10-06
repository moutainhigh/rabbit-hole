package com.github.lotus.sso;

import cn.hutool.core.lang.Assert;
import com.github.lotus.chaos.api.AccountApi;
import com.github.lotus.chaos.vo.UserDetailVo;
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
    private final AccountApi accountApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailVo user = accountApi.getUser(username);
        Assert.notNull(user);
        return new User(username, user.getPassword(), Collections.emptyList());
    }

}
