package in.hocg.rabbit.sso.config.security.support.mobile;

import in.hocg.rabbit.sso.config.security.SecuritySupportService;
import in.hocg.rabbit.sso.config.security.handler.AjaxAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by hocgin on 2021/6/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RequiredArgsConstructor
public class MobileSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AjaxAuthenticationEntryPoint authenticationEntryPoint;
    private final SecuritySupportService supportService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
        mobileAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        mobileAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        mobileAuthenticationFilter.setAuthenticationEntryPoint(authenticationEntryPoint);

        MobileAuthenticationProvider authenticationProvider = new MobileAuthenticationProvider();
        authenticationProvider.setChecker(new AccountStatusUserDetailsChecker());
        authenticationProvider.setSupportService(supportService);
        builder.authenticationProvider(authenticationProvider)
            .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
