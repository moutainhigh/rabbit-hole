package in.hocg.rabbit.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.com.api.UniqueCodeServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.mall.biz.convert.OrderDeliveryConvert;
import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.rabbit.mall.biz.mapper.OrderDeliveryMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderDeliveryMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderDeliveryPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShippedOrderBySellerRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderDeliveryService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * [订单模块] 配送单 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@UseConvert(OrderDeliveryConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderDeliveryServiceImpl extends AbstractServiceImpl<OrderDeliveryMapper, OrderDelivery> implements OrderDeliveryService {
    private final OrderDeliveryMapping mapping;
    private final OrderDeliveryConvert convert;
    private final UniqueCodeServiceApi codeServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Long orderId, ShippedOrderBySellerRo ro) {
        OrderDelivery entity = mapping.asOrderDelivery(ro);
        entity.setOrderId(orderId);
        entity.setEncoding(codeServiceApi.getUniqueCode(CodeType.DeliveryOrder.getCodeStr()));
        validInsert(entity);
    }

    @Override
    public IPage<OrderDeliveryOrdinaryVo> paging(OrderDeliveryPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asOrderDeliveryOrdinaryVo);
    }

    @Override
    public OrderDeliveryComplexVo getComplex(Long id) {
        return convert.asOrderDeliveryComplexVo(getById(id));
    }

    @Override
    public OrderDelivery getByOrderId(Long orderId) {
        return lambdaQuery().eq(OrderDelivery::getOrderId, orderId).one();
    }
}
