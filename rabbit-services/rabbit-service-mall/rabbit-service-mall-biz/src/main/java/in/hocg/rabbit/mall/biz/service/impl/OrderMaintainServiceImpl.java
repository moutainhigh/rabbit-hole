package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.com.api.SnCodeServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.mall.api.enums.maintain.OrderMaintainStatus;
import in.hocg.rabbit.mall.biz.convert.OrderMaintainConvert;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.mapper.OrderMaintainMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderMaintainMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.CloseByBuyerRo;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderMaintainPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.PassOrderMaintainRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import in.hocg.rabbit.mall.biz.service.OrderMaintainService;
import in.hocg.rabbit.mall.biz.state.maintain.MaintainStateMachine;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [订单模块] 退货申请 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@UseConvert(OrderMaintainConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderMaintainServiceImpl extends AbstractServiceImpl<OrderMaintainMapper, OrderMaintain> implements OrderMaintainService {
    private final OrderMaintainMapping mapping;
    private final OrderItemService orderItemService;
    private final SnCodeServiceApi codeServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Long orderItemId, MaintainClientRo ro) {
        OrderItem orderItem = Assert.notNull(orderItemService.getById(orderItemId), "订单项不存在");

        Integer quantity = orderItem.getQuantity();
        BigDecimal realAmt = orderItem.getRealAmt();
        OrderMaintain entity = mapping.asOrderMaintain(ro);
        entity.setOwnerUserId(ro.getOperatorId());
        entity.setEncoding(codeServiceApi.getSnCode(CodeType.MaintainOrder.getCodeStr()));
        entity.setStatus(OrderMaintainStatus.Processing.getCode());
        entity.setRefundQuantity(quantity);
        entity.setRefundAmt(realAmt);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pass(Long id, PassOrderMaintainRo ro) {
        OrderMaintain entity = Assert.notNull(getById(id), "未找到售后申请");
        MaintainStateMachine.pass(entity, ro);

        OrderMaintain update = new OrderMaintain();
        update.setId(id);
        update.setHandleAt(LocalDateTime.now());
        update.setHandlerUserId(ro.getOperatorId());
        update.setHandleRemark(ro.getHandleRemark());
        updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeByBuyer(Long id, CloseByBuyerRo ro) {
        OrderMaintain entity = getByIdAndOwnerUserId(id, ro.getOperatorId()).orElseThrow(() -> new IllegalArgumentException("未找到售后申请"));
        MaintainStateMachine.closeByBuyer(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeBySeller(Long id) {
        OrderMaintain entity = Assert.notNull(getById(id), "未找到售后申请");
        MaintainStateMachine.closeBySeller(entity);
    }

    @Override
    public IPage<OrderMaintainOrdinaryVo> paging(OrderMaintainPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(item -> as(item, OrderMaintainOrdinaryVo.class));
    }

    @Override
    public OrderMaintainComplexVo getComplex(Long id) {
        return mapping.asOrderMaintainComplexVo(getById(id));
    }

    private Optional<OrderMaintain> getByIdAndOwnerUserId(Long id, Long ownerUserId) {
        return lambdaQuery().eq(OrderMaintain::getId, id).eq(OrderMaintain::getOwnerUserId, ownerUserId).oneOpt();
    }
}
