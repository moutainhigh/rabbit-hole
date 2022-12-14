package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.OrderDiscount;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;

import java.util.List;

/**
 * <p>
 * [订单模块] 订单优惠详项表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface OrderDiscountService extends AbstractService<OrderDiscount> {

    List<OrderDiscount> listByOrderId(Long orderId);

    void usedDiscount(Long orderId, List<CalcOrderVo.DiscountVo> discounts);

    void refundDiscountByOrderId(Long orderId);
}
