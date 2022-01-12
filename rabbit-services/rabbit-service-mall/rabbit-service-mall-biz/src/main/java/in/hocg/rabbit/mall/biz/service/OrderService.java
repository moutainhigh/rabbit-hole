package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderOrdinaryVo;

import java.util.Optional;

/**
 * <p>
 * [订单模块] 订单主表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface OrderService extends AbstractService<Order> {

    IPage<OrderOrdinaryVo> paging(OrderPagingRo ro);

    OrderComplexVo getComplex(Long id);

    void delete(DeleteRo ro);

    void updateOne(Long id, UpdateOrderRo ro);

    String getCashierUrl(String orderNo);

    CalcOrderVo calcOrder(CalcOrderRo ro);

    String createOrder(CreateOrderRo ro);

    void closeBySeller(Long id, CloseOrderManageRo ro);

    void closeByBuyer(Long id, CloseOrderClientRo ro);

    void payResult(OrderPayResultRo ro);

    void refundByBuyer(Long id, RefundOrderClientRo ro);

    void refundBySeller(Long id, RefundOrderManageRo ro);

    void receivedByBuyer(Long id, ReceivedOrderClientRo ro);

    void shipped(Long id, ShippedOrderBySellerRo ro);

    Optional<Order> getByOrderItemId(Long orderItemId);

    void adjustment(Long id, AdjustmentOrderBySellerRo ro);
}
