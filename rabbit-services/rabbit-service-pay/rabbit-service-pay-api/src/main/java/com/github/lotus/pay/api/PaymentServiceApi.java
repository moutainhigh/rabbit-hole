package com.github.lotus.pay.api;


import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.ro.QueryPaymentWayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;

import java.util.List;

/**
 * Created by hocgin on 2020/6/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface PaymentServiceApi {

    /**
     * 关闭交易单
     *
     * @param tradeSn
     */
    void closeTrade(String tradeSn);

    /**
     * 创建交易单
     *
     * @param ro
     * @return
     */
    String createTrade(CreateTradeRo ro);

    /**
     * 查询支付方式
     *
     * @param ro
     * @return
     */
    List<PaymentWayVo> queryPaymentWay(QueryPaymentWayRo ro);

    /**
     * 去支付
     *
     * @param ro
     * @return
     */
    GoPayVo goPay(GoPayRo ro);

    /**
     * 查询交易单信息
     *
     * @param tradeSn
     * @return
     */
    QueryAsyncVo<TradeStatusSync> queryTrade(String tradeSn);

    /**
     * 查询退款单信息
     *
     * @param refundSn
     * @return
     */
    QueryAsyncVo<RefundStatusSync> queryRefund(String refundSn);
}
