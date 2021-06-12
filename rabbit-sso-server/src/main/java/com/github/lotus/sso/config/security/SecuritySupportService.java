package com.github.lotus.sso.config.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Created by hocgin on 2021/6/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SecuritySupportService {

    Optional<UserDetails> getUserDetailsByMobile(String mobile);
}
