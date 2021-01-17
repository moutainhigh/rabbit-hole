package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.CommentTarget;
import com.github.lotus.com.biz.mapper.CommentTargetMapper;
import com.github.lotus.com.biz.mapstruct.CommentTargetMapping;
import com.github.lotus.com.biz.service.CommentTargetService;
import com.github.lotus.common.datadict.CommentTargetType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * [通用模块] 评论对象表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentTargetServiceImpl extends AbstractServiceImpl<CommentTargetMapper, CommentTarget>
    implements CommentTargetService {
    private final CommentTargetMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long getOrCreateCommentTarget(String relType, Long relId) {
        final CommentTargetType targetType = ICode.ofThrow(relType, CommentTargetType.class);
        final Optional<Long> idOpt = getCommentTarget(relType, relId);
        return idOpt.orElseGet(() -> {
            final CommentTarget entity = new CommentTarget()
                .setRelId(relId).setRelType(targetType.getCodeStr());
            validInsert(entity);
            return entity.getId();
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Long> getCommentTarget(String relType, Long relId) {
        final CommentTargetType targetType = ICode.ofThrow(relType, CommentTargetType.class);
        return selectOneByRelTypeAndRelId(targetType.getCodeStr(), relId);
    }

    private Optional<Long> selectOneByRelTypeAndRelId(String relType, Long relId) {
        return lambdaQuery().eq(CommentTarget::getRelType, relType)
            .eq(CommentTarget::getRelId, relId)
            .oneOpt()
            .map(CommentTarget::getId);
    }
}
