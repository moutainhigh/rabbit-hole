package com.github.lotus.com.biz.service;

import com.github.lotus.com.biz.entity.Feedback;
import com.github.lotus.com.biz.pojo.ro.FeedbackPostRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [通用模块] 用户反馈表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-10
 */
public interface FeedbackService extends AbstractService<Feedback> {

    void insertOne(FeedbackPostRo ro);
}
