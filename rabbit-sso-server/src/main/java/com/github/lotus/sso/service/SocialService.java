package com.github.lotus.sso.service;

import java.util.Optional;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SocialService {
    Optional<Object> getAccountBySocial(String registrationId, String principalName);
}
