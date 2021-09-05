package com.github.lotus.com.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.Comment;
import com.github.lotus.com.biz.entity.CommentUserAction;
import com.github.lotus.com.biz.mapper.CommentMapper;
import com.github.lotus.com.biz.mapstruct.CommentMapping;
import com.github.lotus.com.biz.message.MessageTopic;
import com.github.lotus.com.biz.pojo.dto.TriggerCommentedDto;
import com.github.lotus.com.biz.pojo.ro.*;
import com.github.lotus.com.biz.pojo.vo.CommentClientVo;
import com.github.lotus.com.biz.pojo.vo.CommentComplexVo;
import com.github.lotus.com.biz.pojo.vo.CommentUserVo;
import com.github.lotus.com.biz.pojo.vo.RootCommentComplexVo;
import com.github.lotus.com.biz.service.CommentService;
import com.github.lotus.com.biz.service.CommentTargetService;
import com.github.lotus.com.biz.service.CommentUserActionService;
import com.github.lotus.common.datadict.com.CommentUserActionType;
import com.github.lotus.common.datadict.common.RefType;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.message.autoconfigure.service.normal.NormalMessageBervice;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.utils.PageUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>
 * [通用模块] 评论表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentServiceImpl extends TreeServiceImpl<CommentMapper, Comment>
    implements CommentService {
    private final CommentUserActionService commentUserActionService;
    private final CommentTargetService commentTargetService;
    private final UserServiceApi accountServiceApi;
    private final CommentMapping mapping;
    private final NormalMessageBervice messageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, CommentUpdateRo ro) {
        LocalDateTime createdAt = LocalDateTime.now();

        final Comment entity = mapping.asComment(ro);
        entity.setLastUpdatedAt(createdAt);
        entity.setLastUpdater(ro.getUserId());
        validUpdateById(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(CommentInsertRo ro) {
        final Long creatorId = ro.getUserId();
        final String refType = ro.getRefType();
        final Long refId = ro.getRefId();
        Long parentId = ro.getParentId();
        LocalDateTime now = LocalDateTime.now();

        final Long targetId = commentTargetService.getOrCreate(refType, refId);

        final Comment entity = mapping.asComment(ro);
        entity.setEnabled(true);
        entity.setTargetId(targetId);
        entity.setCreatedAt(now);
        entity.setCreator(creatorId);
        validInsert(entity);
        Long commentId = entity.getId();

        // 评论被评论
        if (Objects.nonNull(parentId)) {
            TriggerCommentedDto payload = new TriggerCommentedDto();
            payload.setBeCommentedId(parentId);
            payload.setCreatedAt(now);
            payload.setCreatorId(creatorId);
            payload.setCommentId(commentId);
            messageService.asyncSend(MessageTopic.TriggerCommented.getCode(), MessageBuilder.withPayload(payload).build());
        }
        // 评论对象被评论
        else {

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<RootCommentComplexVo> pagingRootComment(RootCommentPagingRo ro) {
        final String refType = ro.getRefType();
        final Long refId = ro.getRefId();

        final Optional<RefType> targetTypeOpt = ICode.of(refType, RefType.class);
        if (!targetTypeOpt.isPresent()) {
            return PageUtils.emptyPage(ro);
        }

        final Optional<Long> targetIdOpt = commentTargetService.getIdByRefTypeAndRefId(refType, refId);
        if (!targetIdOpt.isPresent()) {
            return PageUtils.emptyPage(ro);
        }

        final Long targetId = targetIdOpt.get();
        final IPage<Comment> result = baseMapper.pagingRootCommend(targetId, true, ro.ofPage());
        return result.convert(entity -> {
            final RootCommentComplexVo item = mapping.asRootCommentComplexVo(this.convertComplex(entity));
            final String treePath = entity.getTreePath() + "/";
            item.setChildTotal(countRightLikeTreePath(treePath));
            return item;
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<CommentComplexVo> pagingChildComment(ChildCommentPagingRo ro) {
        final Comment pComment = baseMapper.selectById(ro.getParentId());
        if (Objects.isNull(pComment) || Objects.isNull(pComment.getParentId())
            || Boolean.FALSE.equals(pComment.getEnabled())) {
            return PageUtils.emptyPage(ro);
        }

        final String treePath = pComment.getTreePath();
        final String regexTreePath = String.format("%s/.*", treePath);
        final IPage<Comment> result = baseMapper.pagingByRegexTreePath(regexTreePath, ro.ofPage());
        return result.convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void like(CommentLikeRo ro) {
        Long commentId = ro.getId();
        Long userId = ro.getUserId();
        CommentUserAction userAction = commentUserActionService.getOrCreate(commentId, userId);
        CommentUserActionType currentUserAction = ICode.ofThrow(userAction.getAction(), CommentUserActionType.class);
        ((CommentServiceImpl) AopContext.currentProxy()).trigger(commentId, currentUserAction, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dislike(CommentDislikeRo ro) {
        Long commentId = ro.getId();
        Long userId = ro.getUserId();
        CommentUserAction userAction = commentUserActionService.getOrCreate(commentId, userId);
        CommentUserActionType currentUserAction = ICode.ofThrow(userAction.getAction(), CommentUserActionType.class);
        ((CommentServiceImpl) AopContext.currentProxy()).trigger(commentId, currentUserAction, false);
    }

    @Retryable
    public void trigger(Long commentId, CommentUserActionType currentAction, boolean isLikeAction) {
        Comment entity = Assert.notNull(getById(commentId), "评论不存在");
        Long likesCount = entity.getLikesCount();
        Long dislikesCount = entity.getDislikesCount();
        CommentUserActionType updateAction;

        switch (currentAction) {
            case Like: {
                dislikesCount = isLikeAction ? (null) : (dislikesCount + 1);
                likesCount = (likesCount - 1);
                updateAction = isLikeAction ? CommentUserActionType.None : CommentUserActionType.Dislike;
                break;
            }
            case Dislike: {
                dislikesCount = (dislikesCount - 1);
                likesCount = isLikeAction ? (likesCount + 1) : null;
                updateAction = isLikeAction ? CommentUserActionType.Like : CommentUserActionType.None;
                break;
            }
            case None: {
                dislikesCount = isLikeAction ? null : (dislikesCount + 1);
                likesCount = isLikeAction ? (likesCount + 1) : null;
                updateAction = isLikeAction ? CommentUserActionType.Like : CommentUserActionType.Dislike;
            }
            default:
                throw new UnsupportedOperationException();
        }

        commentUserActionService.triggerAction(entity.getId(), updateAction.getCode());
        Comment update = new Comment();
        update.setId(commentId);
        update.setLikesCount(likesCount);
        update.setDislikesCount(dislikesCount);
        this.validUpdateById(update);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<CommentComplexVo> paging(CommentPagingRo ro) {
        IPage<Comment> result = baseMapper.paging(ro, ro.ofPage());
        return result.convert(this::convertComplex);
    }

    @Override
    public CommentClientVo commentWithClient(CommentClientRo ro) {
        Comment entity = mapping.asComment(ro);
        entity.setParentId(ro.getId());
        entity.setTargetId(commentTargetService.getOrCreate(ro.getRefType(), ro.getRefId()));
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreator(ro.getUserId());
        this.validInsert(entity);
        return this.convertCommentClientVo(getById(entity.getId()));
    }

    @Override
    public IPage<CommentClientVo> pagingWithClient(CommentClientPagingRo ro) {
        ro.setTargetId(commentTargetService.getOrCreate(ro.getRefType(), ro.getRefId()));
        return baseMapper.pagingWithClient(ro, ro.ofPage()).convert(this::convertCommentClientVo);
    }

    private CommentClientVo convertCommentClientVo(Comment entity) {
        CommentClientVo result = mapping.asCommentClientVo(entity);
        result.setHasReply(this.hasReply(entity.getId()));

        UserContextHolder.getUserId().flatMap(userId -> commentUserActionService.getActionByCommentIdAndUserId(entity.getId(), userId))
            .ifPresent(result::setAction);

        Long authorId = entity.getCreator();
        if (Objects.nonNull(authorId)) {
            result.setAuthor(getCommentUser(authorId));
        }

        Long parentId = entity.getParentId();
        if (Objects.nonNull(parentId)) {
            Comment comment = getById(parentId);
            result.setReplier(getCommentUser(comment.getCreator()));
        }

        return result;
    }

    private CommentComplexVo convertComplex(Comment entity) {
        final CommentComplexVo result = mapping.asCommentComplexVo(entity);
        final String content = entity.getEnabled() ? result.getContent() : "已删除";
        result.setContent(content);
        final Long parentId = entity.getParentId();
        if (Objects.nonNull(parentId)) {
            final Comment pComment = baseMapper.selectById(parentId);
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
            .setNickname(entity.getNickname());
    }

    private Boolean hasReply(Long id) {
        return has(TreeEntity::getParentId, id, null);
    }

    private Integer countRightLikeTreePath(String treePath) {
        return lambdaQuery().likeRight(Comment::getTreePath, treePath).count();
    }

    @Override
    public void validEntity(Comment entity) {
        super.validEntity(entity);
        final Long targetId = entity.getTargetId();
        if (Objects.nonNull(targetId)) {
            ValidUtils.notNull(commentTargetService.getById(targetId));
        }
    }
}
