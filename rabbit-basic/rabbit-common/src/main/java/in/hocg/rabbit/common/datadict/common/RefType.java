package in.hocg.rabbit.common.datadict.common;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum RefType implements DataDictEnum {
    Unknown("unknown", "未知"),
    RefundRecord("refund_record", "退款记录"),
    TradeOrder("trade_order", "交易单"),
    PayRecord("pay_record", "支付记录"),
    Article("article", "文章"),
    Comment("comment", "评论"),
    UserCoupon("user_coupon", "用户优惠券"),
    GameCard("game_card", "游戏卡带"),
    Product("product", "商品"),
    ;

    private final Serializable code;
    private final String name;
}
