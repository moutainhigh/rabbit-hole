package in.hocg.rabbit.mall.biz.controller.client;

import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"mall::订单", "客户端"})
@RestController
@RequestMapping("/client/order")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderClientController {
    private final OrderService service;

    @UseLogger
    @ApiOperation("前往收银台")
    @PostMapping("/go-cashier")
    public Result<String> goCashier(@RequestParam("orderNo") String orderNo) {
        return Result.success(service.getCashierUrl(orderNo));
    }

    @UseLogger
    @ApiOperation("计算订单价格")
    @PostMapping("/calc")
    public Result<CalcOrderVo> calcOrder(@Validated @RequestBody CalcOrderRo ro) {
        return Result.success(service.calcOrder(ro));
    }

    @UseLogger
    @ApiOperation("创建订单")
    @PostMapping("/create")
    public Result<String> createOrder(@Validated @RequestBody CreateOrderRo ro) {
        return Result.success(service.createOrder(ro));
    }

    @UseLogger
    @ApiOperation("申请退款")
    @PostMapping("/refund")
    public Result<Void> applyRefund(@Validated @RequestBody RefundApplyRo ro) {
        service.applyRefund(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public Result<Void> cancelOrder(@Validated @RequestBody CancelOrderRo ro) {
        service.cancelOrder(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("确认订单")
    @PostMapping("/confirm")
    public Result<Void> confirmOrder(@Validated @RequestBody ConfirmOrderRo ro) {
        service.confirmOrder(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("订单详情")
    @GetMapping("/{id}/complex")
    public Result<OrderComplexVo> getOrder(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

}
