package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.biz.entity.CommentTarget;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [通用模块] 评论对象表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
public interface CommentTargetService extends AbstractService<CommentTarget> {

    Long getOrCreate(String refType, Long refId);

    Optional<Long> getIdByRefTypeAndRefId(String relType, Long relId);
}
