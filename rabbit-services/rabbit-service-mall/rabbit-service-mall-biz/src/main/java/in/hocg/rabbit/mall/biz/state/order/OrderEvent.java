package in.hocg.rabbit.mall.biz.state.order;

/**
 * Created by hocgin on 2022/1/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum OrderEvent {
    Payed,
    Shipped,
    // 收货
    ReceivedByBuyer,
    ReceivedBySystem,
    // 关闭
    CloseByBuyer,
    CloseBySeller,
    CloseBySystem,
    // 退款
    RefundByBuyer,
    RefundBySeller,
    ;
}
