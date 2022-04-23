package in.hocg.rabbit.com.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.tree.TreeServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.rabbit.com.api.pojo.vo.CommentSummaryVo;
import in.hocg.rabbit.com.api.pojo.vo.LastCommentVo;
import in.hocg.rabbit.com.biz.convert.CommentConvert;
import in.hocg.rabbit.com.biz.entity.Comment;
import in.hocg.rabbit.com.biz.entity.CommentUserAction;
import in.hocg.rabbit.com.biz.mapper.CommentMapper;
import in.hocg.rabbit.com.biz.mapstruct.CommentMapping;
import in.hocg.rabbit.com.biz.message.MessageTopic;
import in.hocg.rabbit.com.biz.pojo.dto.TriggerCommentedDto;
import in.hocg.rabbit.com.biz.pojo.ro.comment.CommentClientScrollRo;
import in.hocg.rabbit.com.biz.pojo.ro.comment.CommentReportRo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentClientVo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentUserVo;
import in.hocg.rabbit.com.biz.pojo.vo.RootCommentComplexVo;
import in.hocg.rabbit.com.biz.service.CommentService;
import in.hocg.rabbit.com.biz.service.CommentTargetService;
import in.hocg.rabbit.com.biz.service.CommentUserActionService;
import in.hocg.rabbit.com.biz.pojo.ro.*;
import in.hocg.rabbit.com.api.enums.comment.CommentUserActionType;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.message.autoconfigure.service.normal.NormalMessageBervice;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.tree.TreeEntity;
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
import java.util.*;

import static in.hocg.rabbit.common.constant.GlobalConstant.SQL_LAST_ROW;

/**
 * <p>
 * [通用模块] 评论表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Service
@UseConvert(CommentConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentServiceImpl extends TreeServiceImpl<CommentMapper, Comment>
    implements CommentService {
    private final CommentUserActionService commentUserActionService;
    private final CommentTargetService commentTargetService;
    private final CommentMapping mapping;
    private final NormalMessageBervice messageService;
    private final CommentConvert convert;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, CommentUpdateRo ro) {
        LocalDateTime createdAt = LocalDateTime.now();

        final Comment entity = mapping.asComment(ro);
        entity.setCreatedAt(createdAt);
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
        if (targetTypeOpt.isEmpty()) {
            return PageUtils.emptyPage(ro);
        }

        final Optional<Long> targetIdOpt = commentTargetService.getIdByRefTypeAndRefId(refType, refId);
        if (targetIdOpt.isEmpty()) {
            return PageUtils.emptyPage(ro);
        }

        final Long targetId = targetIdOpt.get();
        final IPage<Comment> result = baseMapper.pagingRootCommend(targetId, true, ro.ofPage());
        return result.convert(entity -> {
            final RootCommentComplexVo item = mapping.asRootCommentComplexVo(convert.convertComplex(entity));
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
        return result.convert(convert::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentClientVo like(CommentLikeRo ro) {
        Long commentId = ro.getCommentId();
        Long userId = ro.getUserId();
        CommentUserAction userAction = commentUserActionService.getOrCreate(commentId, userId);
        CommentUserActionType currentUserAction = ICode.ofThrow(userAction.getAction(), CommentUserActionType.class);
        ((CommentServiceImpl) AopContext.currentProxy()).trigger(commentId, currentUserAction, true);
        return convert.convertCommentClientVo(getById(commentId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentClientVo dislike(CommentDislikeRo ro) {
        Long commentId = ro.getCommentId();
        Long userId = ro.getUserId();
        CommentUserAction userAction = commentUserActionService.getOrCreate(commentId, userId);
        CommentUserActionType currentUserAction = ICode.ofThrow(userAction.getAction(), CommentUserActionType.class);
        ((CommentServiceImpl) AopContext.currentProxy()).trigger(commentId, currentUserAction, false);
        return convert.convertCommentClientVo(getById(commentId));
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
                break;
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
        return result.convert(convert::convertComplex);
    }

    @Override
    public CommentClientVo replyWithClient(CommentClientRo ro) {
        Comment entity = mapping.asComment(ro);
        entity.setParentId(ro.getCommentId());
        entity.setTargetId(commentTargetService.getOrCreate(ro.getRefType(), ro.getRefId()));
        this.validInsert(entity);
        return convert.convertCommentClientVo(getById(entity.getId()));
    }

    @Override
    public IPage<CommentClientVo> pagingWithClient(CommentClientPagingRo ro) {
        Optional<Long> targetOpt = commentTargetService.getIdByRefTypeAndRefId(ro.getRefType(), ro.getRefId());
        if (targetOpt.isEmpty()) {
            return PageUtils.emptyPage(ro);
        }
        ro.setTargetId(targetOpt.get());
        return baseMapper.pagingWithClient(ro, ro.ofPage()).convert(convert::convertCommentClientVo);
    }

    @Override
    public IScroll<CommentClientVo> scrollWithClient(CommentClientScrollRo ro) {
        Optional<Long> targetOpt = commentTargetService.getIdByRefTypeAndRefId(ro.getRefType(), ro.getRefId());
        if (targetOpt.isEmpty()) {
            return PageUtils.emptyScroll();
        }
        ro.setTargetId(targetOpt.get());
        return PageUtils.fillScroll(baseMapper.scrollWithClient(ro, ro.ofPage()), Comment::getId)
            .convert(convert::convertCommentClientVo);
    }

    @Override
    public void report(CommentReportRo ro) {

    }

    @Override
    public Boolean hasReply(Long id) {
        return has(TreeEntity::getParentId, id, null);
    }

    @Override
    public CommentSummaryVo getSummary(String refType, Long refId, Integer limit) {
        CommentSummaryVo result = new CommentSummaryVo();
        List<CommentSummaryVo.LastCommentVo> replyList = Collections.emptyList();
        final Long targetId = commentTargetService.getOrCreate(refType, refId);
        if (limit > 0) {
            replyList = new ArrayList<>(as(lambdaQuery().eq(Comment::getTargetId, targetId)
                .orderByDesc(CommonEntity::getCreatedAt)
                .last("LIMIT " + limit).list(), CommentSummaryVo.LastCommentVo.class));
        }
        result.setLastReplyList(replyList);
        result.setLastReply(CollUtil.getFirst(replyList));
        result.setTotalReply(this.countByTargetId(targetId));
        return result;
    }

    private Long countByTargetId(Long targetId) {
        return lambdaQuery().eq(Comment::getTargetId, targetId).count();
    }


    private Long countRightLikeTreePath(String treePath) {
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
