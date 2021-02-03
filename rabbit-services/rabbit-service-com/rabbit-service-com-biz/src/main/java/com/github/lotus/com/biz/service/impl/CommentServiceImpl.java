package com.github.lotus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.Comment;
import com.github.lotus.com.biz.mapper.CommentMapper;
import com.github.lotus.com.biz.mapstruct.CommentMapping;
import com.github.lotus.com.biz.pojo.ro.ChildCommentPagingRo;
import com.github.lotus.com.biz.pojo.ro.CommentInsertRo;
import com.github.lotus.com.biz.pojo.ro.CommentLikeRo;
import com.github.lotus.com.biz.pojo.ro.CommentPagingRo;
import com.github.lotus.com.biz.pojo.ro.CommentUpdateRo;
import com.github.lotus.com.biz.pojo.ro.RootCommentPagingRo;
import com.github.lotus.com.biz.pojo.vo.CommentComplexVo;
import com.github.lotus.com.biz.pojo.vo.CommentUserVo;
import com.github.lotus.com.biz.pojo.vo.RootCommentComplexVo;
import com.github.lotus.com.biz.service.CommentService;
import com.github.lotus.com.biz.service.CommentTargetService;
import com.github.lotus.common.datadict.CommentTargetType;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeServiceImpl;

import in.hocg.boot.mybatis.plus.autoconfiguration.utils.PageUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
    private final CommentTargetService commentTargetService;
    private final UserServiceApi accountServiceApi;
    private final CommentMapping mapping;

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
        LocalDateTime now = LocalDateTime.now();

        final Long targetId = commentTargetService.getOrCreate(refType, refId);

        final Comment entity = mapping.asComment(ro);
        entity.setEnabled(true);
        entity.setTargetId(targetId);
        entity.setCreatedAt(now);
        entity.setCreator(creatorId);
        validInsert(entity);

        // 触发消息
//        final SubscriptionEvent message = new SubscriptionEvent()
//            .setActorId(creatorId)
//            .setSubjectId(entity.getId())
//            .setSubjectType(SubjectType.Comment)
//            .setNotifyType(NotifyType.SubscriptionComment);
//        MessageFactory.local().publish(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<RootCommentComplexVo> pagingRootComment(RootCommentPagingRo ro) {
        final String refType = ro.getRefType();
        final Long refId = ro.getRefId();

        final Optional<CommentTargetType> targetTypeOpt = ICode.of(refType, CommentTargetType.class);
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
        Long id = ro.getId();
        Comment entity = getById(id);
        Long likesCount = entity.getLikesCount();

        Comment update = new Comment();
        update.setId(id);
        update.setLikesCount(likesCount + 1);
        this.validUpdateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<CommentComplexVo> paging(CommentPagingRo ro) {
        IPage<Comment> result = baseMapper.paging(ro, ro.ofPage());
        return result.convert(this::convertComplex);
    }

    private CommentComplexVo convertComplex(Comment entity) {
        final CommentComplexVo result = mapping.asCommentComplexVo(entity);
        final String content = Boolean.FALSE.equals(entity.getEnabled())
            ? result.getContent() : "已删除";
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

    public Integer countRightLikeTreePath(String treePath) {
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
