package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.Feedback;
import com.github.lotus.com.biz.mapper.FeedbackMapper;
import com.github.lotus.com.biz.mapstruct.FeedbackMapping;
import com.github.lotus.com.biz.pojo.ro.FeedbackPostRo;
import com.github.lotus.com.biz.service.FeedbackService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * [通用模块] 用户反馈表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FeedbackServiceImpl extends AbstractServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    private final FeedbackMapping mapping;

    @Override
    public void insertOne(FeedbackPostRo ro) {
        LocalDateTime now = LocalDateTime.now();

        Feedback entity = mapping.asFeedback(ro);
        entity.setCreatedAt(now);
        this.validInsert(entity);
    }
}
