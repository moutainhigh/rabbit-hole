package com.github.lotus.sso.config.security.support.mobile;

import com.github.lotus.sso.config.security.SecuritySupportService;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import java.util.Optional;

/**
 * Created by hocgin on 2021/6/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsChecker checker;
    @Setter
    private SecuritySupportService securitySupportService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;

        String mobile = String.valueOf(mobileAuthenticationToken.getPrincipal());
        Optional<UserDetails> userDetailsOptional = securitySupportService.getUserDetailsByMobile(mobile);
        if (!userDetailsOptional.isPresent()) {
            log.debug("Authentication failed");
            throw new BadCredentialsException("Noop Bind Account");
        }

        UserDetails userDetails = userDetailsOptional.get();
        checker.check(userDetails);

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
