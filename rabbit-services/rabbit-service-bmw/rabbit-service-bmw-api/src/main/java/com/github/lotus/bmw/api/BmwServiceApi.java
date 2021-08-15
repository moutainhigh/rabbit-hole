package com.github.lotus.bmw.api;

import com.github.lotus.bmw.api.pojo.ro.*;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.api.pojo.vo.RefundSyncVo;
import com.github.lotus.bmw.api.pojo.vo.TradeSyncVo;
import com.github.lotus.common.constant.GlobalConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface BmwServiceApi {
    String CONTEXT_ID = "BmwServiceApi";

    /**
     * 关闭交易单
     *
     * @param ro ro
     */
    @PostMapping(value = CONTEXT_ID + "/closeTrade", headers = GlobalConstant.FEIGN_HEADER)
    TradeSyncVo closeTrade(@RequestBody CloseTradeRo ro);

    /**
     * 创建交易单
     *
     * @param ro ro
     * @return _
     */
    @PostMapping(value = CONTEXT_ID + "/createTrade", headers = GlobalConstant.FEIGN_HEADER)
    TradeSyncVo createTrade(@RequestBody CreateTradeRo ro);

    /**
     * 去支付
     *
     * @param ro ro
     * @return _
     */
    @PostMapping(value = CONTEXT_ID + "/payTrade", headers = GlobalConstant.FEIGN_HEADER)
    PayTradeVo goPay(@RequestBody PayTradeRo ro);


    /**
     * 查询交易单信息
     *
     * @param outTradeOrderNo _
     * @return _
     */
    @PostMapping(value = CONTEXT_ID + "/getTrade", headers = GlobalConstant.FEIGN_HEADER)
    TradeSyncVo getTrade(@RequestBody GetTradeRo ro);

    /**
     * 进行退款
     *
     * @param ro ——
     * @return _
     */
    @PostMapping(value = CONTEXT_ID + "/goRefund", headers = GlobalConstant.FEIGN_HEADER)
    RefundSyncVo goRefund(@RequestBody GoRefundRo ro);

    /**
     * 查询退款单信息
     *
     * @param outRefundOrderNo _
     * @return _
     */
    @PostMapping(value = CONTEXT_ID + "/getRefund", headers = GlobalConstant.FEIGN_HEADER)
    RefundSyncVo getRefund(@RequestBody GetRefundRo ro);
}
