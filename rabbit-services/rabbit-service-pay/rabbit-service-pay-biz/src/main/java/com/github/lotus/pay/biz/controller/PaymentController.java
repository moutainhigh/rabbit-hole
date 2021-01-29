package com.github.lotus.pay.biz.controller;

import com.github.lotus.pay.api.pojo.ro.CreateTradeGoPayRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.ro.QueryPaymentWayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.biz.constant.PaymentConstants;
import com.github.lotus.pay.biz.pojo.vo.WaitPaymentTradeVo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2020/5/31.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ApiOperation("pay::支付网关")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping(PaymentConstants.PAYMENT_PREFIX)
public class PaymentController {
    private final AllPaymentService service;

    /**
     * 支付回调
     *
     * @param feature     支付功能: 支付、退款
     * @param platformTyp 支付平台: 微信、支付宝
     * @param appid       支付平台的唯一标识
     * @param paymentWay  支付方式
     * @param data
     * @return
     */
    @UseLogger("支付回调")
    @RequestMapping("/{feature}/{platformTyp}/{appid}/{paymentWay}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> doPayResultMessage(@PathVariable("feature") Integer feature,
                                                     @PathVariable("platformTyp") Integer platformTyp,
                                                     @PathVariable("appid") String appid,
                                                     @PathVariable("paymentWay") Integer paymentWay,
                                                     @RequestBody String data) {
        final MessageContext messageContext = new MessageContext()
            .setAppid(appid)
            .setPlatformTyp(platformTyp)
            .setFeature(feature)
            .setPaymentWay(paymentWay);
        return ResponseEntity.ok(service.handleMessage(messageContext, data));
    }

    @UseLogger("发起去支付")
    @ResponseBody
    @PostMapping("/once-pay")
    public Result<GoPayVo> createAndGoPay(@Validated @RequestBody CreateTradeGoPayRo ro) {
        return Result.success(service.createAndGoPay(ro));
    }

    @UseLogger("发起去支付")
    @ResponseBody
    @PostMapping("/go-pay")
    public Result<GoPayVo> goPay(@Validated @RequestBody GoPayRo ro) {
        return Result.success(service.goPay(ro));
    }

    @UseLogger("查询待支付交易单")
    @ResponseBody
    @GetMapping("/trade")
    public Result<WaitPaymentTradeVo> getWaitPaymentTrade(@RequestParam("tradeSn") String tradeSn) {
        return Result.success(service.getWaitPaymentTrade(tradeSn));
    }

    @UseLogger("查询交易渠道")
    @ResponseBody
    @GetMapping("/payment-way")
    public Result<List<PaymentWayVo>> getPaymentWay(@Validated @RequestBody QueryPaymentWayRo ro) {
        return Result.success(service.queryPaymentWay(ro));
    }
}
