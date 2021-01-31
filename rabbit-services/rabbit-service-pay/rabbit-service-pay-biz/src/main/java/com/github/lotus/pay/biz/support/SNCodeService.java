package com.github.lotus.pay.biz.support;

import cn.hutool.core.lang.Snowflake;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SNCodeService {
    private final Snowflake snowFlake;

    /**
     * 支付交易流水号
     *
     * @return
     */
    public String getTransactionSNCode() {
        return getSNCode(Type.Transaction);
    }

    /**
     * 退款交易流水号
     *
     * @return
     */
    public String getRefundSNCode() {
        return getSNCode(Type.Refund);
    }

    @Getter
    @RequiredArgsConstructor
    enum Type {
        Order("E1"),
        OrderRefundApply("R1"),
        CouponAccount("C1"),
        BusinessLog("B1"),
        Transaction("T1"),
        Refund("RD1");
        private final String code;
    }

    private String getSNCode(Type type) {
        final long code = snowFlake.nextId();
        return type.getCode() + code;
    }

}
