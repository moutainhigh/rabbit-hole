package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.api.pojo.ro.GoRefundRo;
import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.api.pojo.vo.RefundSyncVo;
import com.github.lotus.bmw.biz.entity.Account;
import com.github.lotus.common.datadict.bmw.PaymentMchType;

import java.util.Optional;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PaymentMchDockingService {

    /**
     * 发起支付
     *
     * @param ro
     * @return
     */
    PayTradeVo goPay(PayTradeRo ro);

    /**
     * 发起退款
     *
     * @param ro
     * @return
     */
    RefundSyncVo goRefund(GoRefundRo ro);

    /**
     * 账户
     *
     * @param userId
     * @param accessMchId
     * @param paymentMchId
     * @param useScenes
     * @return
     */
    Optional<Account> getAccount(Long userId, Long accessMchId, Long paymentMchId, String useScenes);

    /**
     * 处理支付结果
     */
    void payResult(PaymentMchType paymentMchType, String paymentMchCode, String ro);
}
