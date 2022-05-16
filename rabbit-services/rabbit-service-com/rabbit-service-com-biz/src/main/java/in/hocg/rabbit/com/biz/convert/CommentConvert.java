package in.hocg.rabbit.com.biz.convert;

import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.com.biz.entity.Comment;
import in.hocg.rabbit.com.biz.mapstruct.CommentMapping;
import in.hocg.rabbit.com.biz.pojo.vo.CommentClientVo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentUserVo;
import in.hocg.rabbit.com.biz.service.CommentService;
import in.hocg.rabbit.com.biz.service.CommentUserActionService;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentConvert {
    private final CommentMapping mapping;
    private final CommentService commentService;
    private final UserServiceApi accountServiceApi;
    private final CommentUserActionService commentUserActionService;

    public CommentClientVo asCommentClientVo(Comment entity) {
        CommentClientVo result = mapping.asCommentClientVo(entity);
        result.setHasReply(commentService.hasReply(entity.getId()));

        Optional<Long> userIdOpt = UserContextHolder.getUserId();
        userIdOpt.flatMap(userId -> commentUserActionService.getActionByCommentIdAndUserId(entity.getId(), userId))
            .ifPresent(result::setAction);
        userIdOpt.ifPresent(aLong -> result.setIsCommenter(LangUtils.equal(aLong, entity.getCreator())));


        Long authorId = entity.getCreator();
        if (Objects.nonNull(authorId)) {
            result.setAuthor(getCommentUser(authorId));
        }

        Long parentId = entity.getParentId();
        if (Objects.nonNull(parentId)) {
            Comment comment = commentService.getById(parentId);
            result.setReplier(getCommentUser(comment.getCreator()));
        }

        return result;
    }

    public CommentComplexVo asComplex(Comment entity) {
        final CommentComplexVo result = mapping.asCommentComplexVo(entity);
        final String content = entity.getEnabled() ? result.getContent() : "已删除";
        result.setContent(content);
        final Long parentId = entity.getParentId();
        if (Objects.nonNull(parentId)) {
            final Comment pComment = commentService.getById(parentId);
            final Long pCommentCreatorId = pComment.getCreator();
            result.setPCommenterId(pCommentCreatorId);
            result.setPCommenter(this.getCommentUser(pCommentCreatorId));
        }
        result.setCommenter(this.getCommentUser(result.getCommenterId()));
        return result;
    }

    private CommentUserVo getCommentUser(Long userId) {
        AccountVo entity = accountServiceApi.getById(userId);
        if (Objects.isNull(entity)) {
            return null;
        }
        return new CommentUserVo()
            .setId(entity.getId())
            .setAvatarUrl(entity.getAvatarUrl())
            .setTitle(entity.getNickname());
    }
}
