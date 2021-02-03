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
    public Long getOrCreate(String refType, Long refId) {
        final CommentTargetType targetType = ICode.ofThrow(refType, CommentTargetType.class);
        final Optional<Long> idOpt = getIdByRefTypeAndRefId(refType, refId);
        return idOpt.orElseGet(() -> {
            final CommentTarget entity = new CommentTarget()
                .setRefId(refId).setRefType(targetType.getCodeStr());
            validInsert(entity);
            return entity.getId();
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Long> getIdByRefTypeAndRefId(String refType, Long refId) {
        final CommentTargetType targetType = ICode.ofThrow(refType, CommentTargetType.class);
        return this.getByRefTypeAndRefId(targetType.getCodeStr(), refId)
            .map(CommentTarget::getId);
    }

    private Optional<CommentTarget> getByRefTypeAndRefId(String refType, Long refId) {
        return lambdaQuery().eq(CommentTarget::getRefType, refType)
            .eq(CommentTarget::getRefId, refId).oneOpt();
    }
}
