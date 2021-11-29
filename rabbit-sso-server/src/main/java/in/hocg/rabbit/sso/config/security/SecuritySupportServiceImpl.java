package in.hocg.rabbit.sso.config.security;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by hocgin on 2021/6/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SecuritySupportServiceImpl implements SecuritySupportService {
    private final UserServiceApi userServiceApi;

    @Override
    public Optional<UserDetails> getUserDetailsByPhone(String phone) {
        UserDetailVo user = userServiceApi.getUserByUsernameOrEmailOrPhone(phone);
        Assert.notNull(user, "账号或密码错误");
        return Optional.of(new User(user.getUsername(), user.getPassword(), user.getEnabled(), user.getExpired(), true, user.getLocked(), Collections.emptyList()));
    }

    @Override
    public Optional<UserDetails> getUserByUsernameOrEmailOrPhone(String username) {
        return getUserDetailsByPhone(username);
    }
}
