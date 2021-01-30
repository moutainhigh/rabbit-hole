package com.github.lotus.pay.biz.apiimpl;

import com.github.lotus.pay.api.PaymentServiceApi;
import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.ro.QueryPaymentModeRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import com.github.lotus.pay.api.pojo.ro.CloseTradeRo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class PaymentServiceApiImpl implements PaymentServiceApi {
    private final AllPaymentService allPaymentService;

    @Override
    public void closeTrade(@Validated CloseTradeRo ro) {
        allPaymentService.closeTrade(ro);
    }

    @Override
    public String createTrade(@Validated CreateTradeRo ro) {
        return allPaymentService.createTrade(ro);
    }

    @Override
    public List<PaymentWayVo> queryPaymentMode(@Validated QueryPaymentModeRo ro) {
        return null;
    }

    @Override
    public GoPayVo goPay(@Validated GoPayRo ro) {
        return allPaymentService.goPay(ro);
    }

    @Override
    public QueryAsyncVo<TradeStatusSync> queryTrade(String tradeSn) {
        return allPaymentService.queryTrade(tradeSn);
    }

    @Override
    public QueryAsyncVo<RefundStatusSync> queryRefund(String refundSn) {
        return allPaymentService.queryRefund(refundSn);
    }
}
