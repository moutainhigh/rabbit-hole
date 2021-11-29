package in.hocg.rabbit.bmw.biz.apiimpl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.bmw.api.BmwServiceApi;
import in.hocg.rabbit.bmw.api.pojo.vo.RefundStatusSyncVo;
import in.hocg.rabbit.bmw.api.pojo.vo.TradeStatusSyncVo;
import in.hocg.rabbit.bmw.biz.service.BmwService;
import in.hocg.rabbit.bmw.biz.service.RefundRecordService;
import in.hocg.rabbit.bmw.biz.service.TradeOrderService;
import in.hocg.rabbit.bmw.api.pojo.ro.*;
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
    private final BmwService bmwService;

    @Override
    public TradeStatusSyncVo createTrade(@Validated @RequestBody CreateTradeRo ro) {
        return tradeOrderService.createTrade(ro);
    }

    @Override
    public TradeStatusSyncVo closeTrade(@Validated @RequestBody CloseTradeRo ro) {
        Assert.isTrue(StrUtil.isNotBlank(ro.getOutTradeNo()) || StrUtil.isNotBlank(ro.getTradeNo()));
        return tradeOrderService.closeTrade(ro);
    }

    @Override
    public String getCashierUrl(@Validated @RequestBody GetCashierRo ro) {
        Assert.isTrue(StrUtil.isNotBlank(ro.getOutTradeNo()) || StrUtil.isNotBlank(ro.getTradeNo()));
        return bmwService.getCashierUrl(ro);
    }

    @Override
    public TradeStatusSyncVo getTrade(@Validated @RequestBody GetTradeRo ro) {
        Assert.isTrue(StrUtil.isNotBlank(ro.getOutTradeNo()) || StrUtil.isNotBlank(ro.getTradeNo()));
        return tradeOrderService.getTrade(ro);
    }

    @Override
    public RefundStatusSyncVo goRefund(@Validated @RequestBody GoRefundRo ro) {
        Assert.isTrue(StrUtil.isNotBlank(ro.getOutTradeNo()) || StrUtil.isNotBlank(ro.getTradeNo()));
        return refundRecordService.goRefund(ro);
    }

    @Override
    public RefundStatusSyncVo getRefund(@Validated @RequestBody GetRefundRo ro) {
        return refundRecordService.getRefund(ro);
    }

}
