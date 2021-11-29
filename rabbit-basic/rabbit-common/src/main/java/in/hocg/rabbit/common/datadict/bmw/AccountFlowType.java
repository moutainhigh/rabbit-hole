package in.hocg.rabbit.common.datadict.bmw;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum AccountFlowType implements DataDictEnum {
    Refund("refund", "退款"),
    Trade("trade", "交易"),
    Withdraw("withdraw", "提现"),
    Ledger("ledger", "分账"),
    Recharge("recharge", "充值"),
    ;
    private final Serializable code;
    private final String name;
}
