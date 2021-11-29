package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.biz.entity.CommentUserAction;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

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
