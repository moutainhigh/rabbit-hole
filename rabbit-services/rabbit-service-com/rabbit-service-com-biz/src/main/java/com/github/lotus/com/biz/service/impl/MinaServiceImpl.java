package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.service.MinaService;
import com.github.lotus.com.biz.service.UserIntegralService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MinaServiceImpl implements MinaService {
    private final UserIntegralService userIntegralService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userSign(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        Boolean hasSign = userIntegralService.exitUserSign(userId, now.toLocalDate());
        if (hasSign) {
            return;
        }
        userIntegralService.userSign(userId, now);
    }
}
