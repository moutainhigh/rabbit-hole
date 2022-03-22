package in.hocg.rabbit.common.datadict.common;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.common.constant.DataDictKeys;
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
@UseDataDictKey(value = DataDictKeys.REF_TYPE, description = "参照类型")
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
    SystemMessage("system_message", "系统消息"),
    PersonalMessage("personal_message", "私信消息"),
    NoticeMessage("notice_message", "订阅消息"),
    ;

    private final Serializable code;
    private final String name;
}
