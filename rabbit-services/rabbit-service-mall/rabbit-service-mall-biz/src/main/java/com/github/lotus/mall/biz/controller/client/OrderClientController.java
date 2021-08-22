package com.github.lotus.mall.biz.controller.client;

import com.github.lotus.mall.biz.pojo.ro.*;
import com.github.lotus.mall.biz.pojo.vo.CalcOrderVo;
import com.github.lotus.mall.biz.pojo.vo.OrderComplexVo;
import com.github.lotus.mall.biz.service.OrderService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/client/order")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderClientController {
    private final OrderService service;

    @UseLogger("前往收银台")
    @PostMapping("/go-cashier")
    public Result<String> goCashier(@RequestParam("orderNo") String orderNo) {
        return Result.success(service.getCashierUrl(orderNo));
    }

    @UseLogger("计算订单价格")
    @PostMapping("/calc")
    public Result<CalcOrderVo> calcOrder(@Validated @RequestBody CalcOrderRo ro) {
        return Result.success(service.calcOrder(ro));
    }

    @UseLogger("创建订单")
    @PostMapping("/create")
    public Result<String> createOrder(@Validated @RequestBody CreateOrderRo ro) {
        return Result.success(service.createOrder(ro));
    }

    @UseLogger("申请退款")
    @PostMapping("/refund")
    public Result<Void> applyRefund(@Validated @RequestBody RefundApplyRo ro) {
        service.applyRefund(ro);
        return Result.success();
    }

    @UseLogger("取消订单")
    @PostMapping("/cancel")
    public Result<Void> cancelOrder(@Validated @RequestBody CancelOrderRo ro) {
        service.cancelOrder(ro);
        return Result.success();
    }

    @UseLogger("确认订单")
    @PostMapping("/confirm")
    public Result<Void> confirmOrder(@Validated @RequestBody ConfirmOrderRo ro) {
        service.confirmOrder(ro);
        return Result.success();
    }

    @UseLogger("订单详情")
    @GetMapping("/{id}/complex")
    public Result<OrderComplexVo> getOrder(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

}
