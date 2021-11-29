package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.biz.entity.Feedback;
import in.hocg.rabbit.com.biz.pojo.ro.FeedbackInsertRo;
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

    void insertOne(FeedbackInsertRo ro);
}
