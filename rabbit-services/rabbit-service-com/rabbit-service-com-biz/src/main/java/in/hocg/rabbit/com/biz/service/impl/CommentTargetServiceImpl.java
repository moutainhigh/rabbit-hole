package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.CommentTarget;
import in.hocg.rabbit.com.biz.mapper.CommentTargetMapper;
import in.hocg.rabbit.com.biz.mapstruct.CommentTargetMapping;
import in.hocg.rabbit.com.biz.service.CommentTargetProxyService;
import in.hocg.rabbit.com.biz.service.CommentTargetService;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
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
    private final CommentTargetProxyService proxyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long getOrCreate(String refType, Long refId) {
        final RefType targetType = ICode.ofThrow(refType, RefType.class);
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
        return this.getByRefTypeAndRefId(refType, refId).map(CommentTarget::getId);
    }

    private Optional<CommentTarget> getByRefTypeAndRefId(String refType, Long refId) {
        return lambdaQuery().eq(CommentTarget::getRefType, refType)
            .eq(CommentTarget::getRefId, refId).oneOpt();
    }
}
