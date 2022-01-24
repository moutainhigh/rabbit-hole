package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.com.api.SnCodeServiceApi;
import in.hocg.rabbit.com.api.UserAddressServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
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
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private final SnCodeServiceApi codeServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Long orderId, ShippedOrderBySellerRo ro) {
        OrderDelivery entity = mapping.asOrderDelivery(ro);
        entity.setEncoding(codeServiceApi.getSnCode(CodeType.DeliveryOrder.getCodeStr()));
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
