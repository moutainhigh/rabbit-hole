package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.api.pojo.ro.GoRefundRo;
import com.github.lotus.bmw.biz.pojo.ro.GoPayRo;
import com.github.lotus.bmw.biz.pojo.vo.GoPayVo;
import com.github.lotus.bmw.api.pojo.vo.RefundStatusSyncVo;
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
    GoPayVo goPay(GoPayRo ro);

    /**
     * 发起退款
     *
     * @param ro
     * @return
     */
    RefundStatusSyncVo goRefund(GoRefundRo ro);

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
    void handlePayResult(PaymentMchType paymentMchType, String paymentMchCode, Long payRecordId, String ro);

}
