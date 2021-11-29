package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.ro.*;

/**
 * <p>
 * [订单模块] 订单主表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface OrderService extends AbstractService<Order> {

    IPage<OrderComplexVo> paging(OrderPagingRo ro);

    OrderComplexVo getComplex(Long id);

    void deleteOne(Long id);

    void shipped(Long id, ShippedOrderRo ro);

    void updateOne(Long id, UpdateOrderRo ro);

    void close(Long id, CloseOrderRo ro);

    String getCashierUrl(String orderNo);

    CalcOrderVo calcOrder(CalcOrderRo ro);

    String createOrder(CreateOrderRo ro);

    void applyRefund(RefundApplyRo ro);

    void cancelOrder(CancelOrderRo ro);

    void confirmOrder(ConfirmOrderRo ro);

    void payResult(OrderPayResultRo ro);
}
