package com.github.lotus.com.biz.service;

import com.github.lotus.com.biz.entity.CommentUserAction;
import com.github.lotus.com.biz.pojo.dto.CreateCommentUserActionDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import org.springframework.retry.annotation.Retryable;

import java.util.Optional;

/**
 * <p>
 * [通用模块] 用户的评论行为表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-09-05
 */
public interface CommentUserActionService extends AbstractService<CommentUserAction> {

    CommentUserAction getOrCreate(Long commentId, Long userId);

    void triggerAction(Long id, String action);

    Optional<CommentUserAction> getByCommentIdAndUserId(Long commentId, Long userId);

    Optional<String> getActionByCommentIdAndUserId(Long commentId, Long userId);
}
