package com.github.lotus.com.biz.service;

import com.github.lotus.com.biz.entity.CommentTarget;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

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

    Long getOrCreateCommentTarget(String refType, Long refId);

    Optional<Long> getCommentTarget(String relType, Long relId);
}
