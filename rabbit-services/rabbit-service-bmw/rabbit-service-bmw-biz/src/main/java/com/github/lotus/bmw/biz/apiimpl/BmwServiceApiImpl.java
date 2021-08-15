package com.github.lotus.bmw.biz.apiimpl;

import com.github.lotus.bmw.api.BmwServiceApi;
import com.github.lotus.bmw.api.pojo.ro.*;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.api.pojo.vo.RefundSyncVo;
import com.github.lotus.bmw.api.pojo.vo.TradeSyncVo;
import com.github.lotus.bmw.biz.service.RefundRecordService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class BmwServiceApiImpl implements BmwServiceApi {
    private final TradeOrderService tradeOrderService;
    private final RefundRecordService refundRecordService;

    @Override
    public TradeSyncVo createTrade(@Validated @RequestBody CreateTradeRo ro) {
        return tradeOrderService.createTrade(ro);
    }

    @Override
    public TradeSyncVo closeTrade(@Validated @RequestBody CloseTradeRo ro) {
        return tradeOrderService.closeTrade(ro);
    }

    @Override
    public PayTradeVo goPay(@Validated @RequestBody PayTradeRo ro) {
        return tradeOrderService.goPay(ro);
    }

    @Override
    public TradeSyncVo getTrade(@Validated @RequestBody GetTradeRo ro) {
        return tradeOrderService.getTrade(ro);
    }

    @Override
    public RefundSyncVo goRefund(@Validated @RequestBody GoRefundRo ro) {
        return refundRecordService.goRefund(ro);
    }

    @Override
    public RefundSyncVo getRefund(@Validated @RequestBody GetRefundRo ro) {
        return refundRecordService.getRefund(ro);
    }

}
