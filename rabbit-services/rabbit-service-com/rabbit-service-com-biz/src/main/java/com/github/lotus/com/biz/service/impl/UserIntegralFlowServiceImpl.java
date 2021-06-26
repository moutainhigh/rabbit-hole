package com.github.lotus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.UserIntegralFlow;
import com.github.lotus.com.biz.mapper.UserIntegralFlowMapper;
import com.github.lotus.com.biz.mapstruct.UserIntegralFlowMapping;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import com.github.lotus.com.biz.service.UserIntegralFlowService;
import com.github.lotus.common.datadict.com.integralflow.EventType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * [通用] 用户积分流水表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserIntegralFlowServiceImpl extends AbstractServiceImpl<UserIntegralFlowMapper, UserIntegralFlow> implements UserIntegralFlowService {
    private final UserIntegralFlowMapping mapping;

    @Override
    public IPage<MinaIntegralFlowVo> pageFlow(MinaIntegralFlowPageRo ro) {
        return baseMapper.pageFlow(ro, ro.ofPage())
            .convert(this::convertMinaIntegralFlowVo);
    }

    @Override
    public Boolean exitUserSign(Long userId, LocalDate now) {
        return lambdaQuery().eq(UserIntegralFlow::getUserId, userId)
            .eq(UserIntegralFlow::getEventType, EventType.UserSign.getCode())
            .ge(UserIntegralFlow::getCreatedAt, now.atStartOfDay())
            .le(UserIntegralFlow::getCreatedAt, now.plusDays(1).atStartOfDay()).count() > 0;
    }

    private MinaIntegralFlowVo convertMinaIntegralFlowVo(UserIntegralFlow entity) {
        return mapping.asMinaIntegralFlowVo(entity);
    }
}
