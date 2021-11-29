package in.hocg.rabbit.sso.config.security.user;

import in.hocg.rabbit.sso.config.security.SecuritySupportService;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2020/1/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final SecuritySupportService supportService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return supportService.getUserByUsernameOrEmailOrPhone(username)
            .orElseThrow(() -> ServiceException.wrap("账号或密码错误"));
    }

}
