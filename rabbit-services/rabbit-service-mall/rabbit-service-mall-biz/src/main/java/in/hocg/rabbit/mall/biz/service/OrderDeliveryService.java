package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.entity.OrderDelivery;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderDeliveryPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderMaintainPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShippedOrderBySellerRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;

/**
 * <p>
 * [订单模块] 配送单 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface OrderDeliveryService extends AbstractService<OrderDelivery> {

    void create(Long orderId, ShippedOrderBySellerRo ro);

    IPage<OrderDeliveryOrdinaryVo> paging(OrderDeliveryPagingRo ro);

    OrderDeliveryComplexVo getComplex(Long id);
}
