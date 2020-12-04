package com.github.lotus.sso.service.impl;

import com.github.lotus.sso.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SocialServiceImpl implements SocialService {

    @Override
    public Optional<Object> getAccountBySocial(String registrationId, String principalName) {
        return Optional.empty();
    }

}
