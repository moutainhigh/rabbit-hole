package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.Feedback;
import com.github.lotus.com.biz.mapper.FeedbackMapper;
import com.github.lotus.com.biz.service.FeedbackService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

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

}
