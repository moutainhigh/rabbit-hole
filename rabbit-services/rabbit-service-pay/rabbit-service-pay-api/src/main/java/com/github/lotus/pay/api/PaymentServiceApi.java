package com.github.lotus.pay.api;


import com.github.lotus.pay.api.pojo.ro.CloseTradeRo;
import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.ro.QueryPaymentModeRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2020/6/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface PaymentServiceApi {
    String CONTEXT_ID = "PaymentServiceApi";

    /**
     * 关闭交易单
     *
     * @param ro
     */
    @PostMapping(CONTEXT_ID + "/closeTrade")
    void closeTrade(@RequestBody CloseTradeRo ro);

    /**
     * 创建交易单
     *
     * @param ro
     * @return
     */
    @PostMapping(CONTEXT_ID + "/createTrade")
    String createTrade(@RequestBody CreateTradeRo ro);

    /**
     * 查询支付方式
     *
     * @param ro
     * @return
     */
    @PostMapping(CONTEXT_ID + "/queryPaymentMode")
    List<PaymentWayVo> queryPaymentMode(@RequestBody QueryPaymentModeRo ro);

    /**
     * 去支付
     *
     * @param ro
     * @return
     */
    @PostMapping(CONTEXT_ID + "/goPay")
    GoPayVo goPay(@RequestBody GoPayRo ro);

    /**
     * 查询交易单信息
     *
     * @param tradeSn
     * @return
     */
    @PostMapping(CONTEXT_ID + "/queryTrade")
    QueryAsyncVo<TradeStatusSync> queryTrade(@RequestParam("tradeSn") String tradeSn);

    /**
     * 查询退款单信息
     *
     * @param refundSn
     * @return
     */
    @PostMapping(CONTEXT_ID + "/queryRefund")
    QueryAsyncVo<RefundStatusSync> queryRefund(@RequestParam("refundSn") String refundSn);
}
