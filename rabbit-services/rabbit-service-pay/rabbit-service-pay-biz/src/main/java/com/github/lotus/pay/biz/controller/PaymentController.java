package com.github.lotus.pay.biz.controller;

import com.github.lotus.pay.api.pojo.ro.CreateTradeGoPayRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.biz.constant.PaymentConstants;
import com.github.lotus.pay.biz.pojo.vo.WaitPayTradeVo;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.support.payment.resolve.message.MessageContext;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import in.hocg.boot.web.utils.web.RequestUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ApiOperation("pay::支付网关")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping(PaymentConstants.PAYMENT_PREFIX)
public class PaymentController {
    private final AllPaymentService service;

    @UseLogger("支付回调")
    @RequestMapping("/{feature}/{platformTyp}/{accessAppSn}")
    public ResponseEntity<String> doPayResultMessage(@PathVariable("feature") String feature,
                                                     @PathVariable("platformTyp") String platformType,
                                                     @PathVariable("accessAppSn") String accessAppSn,
                                                     @RequestBody String data) {
        final MessageContext messageContext = new MessageContext()
            .setAccessAppSn(accessAppSn).setPlatform(platformType).setFeature(feature);
        return ResponseEntity.ok(service.handleMessage(messageContext, data));
    }

    @UseLogger("发起去支付")
    @ResponseBody
    @PostMapping("/once-pay")
    public Result<GoPayVo> createAndGoPay(@Validated @RequestBody CreateTradeGoPayRo ro, HttpServletRequest request) {
        ro.setClientIp(RequestUtils.getClientIp(request));
        return Result.success(service.createAndGoPay(ro));
    }

    @UseLogger("发起去支付")
    @ResponseBody
    @PostMapping("/go-pay")
    public Result<GoPayVo> goPay(@Validated @RequestBody GoPayRo ro, HttpServletRequest request) {
        ro.setClientIp(RequestUtils.getClientIp(request));
        return Result.success(service.goPay(ro));
    }

    @UseLogger("查询待支付交易单")
    @ResponseBody
    @GetMapping("/trade")
    public Result<WaitPayTradeVo> getTrade(@RequestParam("tradeSn") String tradeSn) {
        return Result.success(service.getWaitTrade(tradeSn));
    }

//    @UseLogger("查询交易渠道")
//    @ResponseBody
//    @GetMapping("/payment-way")
//    public Result<List<PaymentWayVo>> getPaymentWay(@Validated @RequestBody QueryPaymentWayRo ro) {
//        return Result.success(service.queryPaymentWay(ro));
//    }
}
