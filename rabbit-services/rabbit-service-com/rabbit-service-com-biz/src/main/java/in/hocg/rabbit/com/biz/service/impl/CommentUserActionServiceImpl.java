package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.CommentUserAction;
import in.hocg.rabbit.com.biz.mapper.CommentUserActionMapper;
import in.hocg.rabbit.com.biz.service.CommentUserActionService;
import in.hocg.rabbit.com.api.enums.comment.CommentUserActionType;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [通用模块] 用户的评论行为表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-09-05
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentUserActionServiceImpl extends AbstractServiceImpl<CommentUserActionMapper, CommentUserAction> implements CommentUserActionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentUserAction getOrCreate(Long commentId, Long userId) {
        Optional<CommentUserAction> entityOpt = getByCommentIdAndUserId(commentId, userId);
        if (entityOpt.isPresent()) {
            return entityOpt.get();
        }
        CommentUserAction entity = new CommentUserAction();
        entity.setUserId(userId);
        entity.setAction(CommentUserActionType.None.getCode());
        entity.setCommentId(commentId);
        entity.setCreatedAt(LocalDateTime.now());
        this.validInsertOrUpdate(entity);
        return entity;
    }

    @Override
    public void triggerAction(Long id, String action) {
        lambdaUpdate().eq(CommentUserAction::getId, id)
            .set(CommentUserAction::getLastUpdatedAt, LocalDateTime.now())
            .set(CommentUserAction::getAction, action).update();
    }

    @Override
    public Optional<CommentUserAction> getByCommentIdAndUserId(Long commentId, Long userId) {
        return lambdaQuery().eq(CommentUserAction::getCommentId, commentId)
            .eq(CommentUserAction::getUserId, userId).oneOpt();
    }

    @Override
    public Optional<String> getActionByCommentIdAndUserId(Long commentId, Long userId) {
        return lambdaQuery().select(CommentUserAction::getAction).eq(CommentUserAction::getCommentId, commentId)
            .eq(CommentUserAction::getUserId, userId).oneOpt().map(CommentUserAction::getAction);
    }
}
