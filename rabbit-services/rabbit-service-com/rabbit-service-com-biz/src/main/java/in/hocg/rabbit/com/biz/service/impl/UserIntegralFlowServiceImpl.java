package in.hocg.rabbit.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.biz.entity.UserIntegralFlow;
import in.hocg.rabbit.com.biz.mapper.UserIntegralFlowMapper;
import in.hocg.rabbit.com.biz.mapstruct.UserIntegralFlowMapping;
import in.hocg.rabbit.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import in.hocg.rabbit.com.biz.pojo.vo.MinaIntegralFlowVo;
import in.hocg.rabbit.com.biz.service.UserIntegralFlowService;
import in.hocg.rabbit.common.datadict.com.integralflow.ChangeType;
import in.hocg.rabbit.common.datadict.com.integralflow.EventType;
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
    public Integer countSignByDate(Long userId, LocalDate now) {
        return this.countByUserIdAndEventTypeAndLocalDate(userId, EventType.UserSign.getCodeStr(), now);
    }


    @Override
    public Integer countWatchAdByDate(Long userId, LocalDate now) {
        return countByUserIdAndEventTypeAndLocalDate(userId, EventType.WatchAd.getCodeStr(), now);
    }

    /**
     * 查询某一天内触发积分增加事件的数量
     *
     * @param userId
     * @param eventType
     * @param now
     * @return
     */
    private Integer countByUserIdAndEventTypeAndLocalDate(Long userId, String eventType, LocalDate now) {
        return lambdaQuery().eq(UserIntegralFlow::getUserId, userId)
            .eq(UserIntegralFlow::getEventType, eventType)
            .eq(UserIntegralFlow::getChangeType, ChangeType.Plus.getCodeStr())
            .ge(UserIntegralFlow::getCreatedAt, now.atStartOfDay())
            .le(UserIntegralFlow::getCreatedAt, now.plusDays(1).atStartOfDay()).count();
    }

    private MinaIntegralFlowVo convertMinaIntegralFlowVo(UserIntegralFlow entity) {
        return mapping.asMinaIntegralFlowVo(entity);
    }
}
