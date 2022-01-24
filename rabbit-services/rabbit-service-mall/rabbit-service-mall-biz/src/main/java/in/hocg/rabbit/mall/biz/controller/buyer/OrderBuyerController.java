package in.hocg.rabbit.mall.biz.controller.buyer;

import in.hocg.rabbit.mall.biz.pojo.vo.CalcOrderVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
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
@Api(tags = {"mall::订单(买家)", "买家", "mall"})
@RestController
@RequestMapping("/buyer/order")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderBuyerController {
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
    public Result<CalcOrderVo> calc(@Validated @RequestBody CalcOrderRo ro) {
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        return Result.success(service.calcOrder(ro));
    }

    @UseLogger
    @ApiOperation("创建订单")
    @PostMapping("/create")
    public Result<String> create(@Validated @RequestBody CreateOrderRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.createOrder(ro));
    }

    @UseLogger
    @ApiOperation(value = "关闭支付", notes = "仅待支付订单可以关闭")
    @PostMapping("/{id}/close")
    public Result<Void> close(@PathVariable("id") Long id, @Validated @RequestBody CloseOrderClientRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.closeByBuyer(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation(value = "退款订单", notes = "仅待发货订单可以退款")
    @PostMapping("/{id}/refund")
    public Result<Void> refund(@PathVariable("id") Long id, @Validated @RequestBody RefundOrderClientRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.refundByBuyer(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation(value = "确认收货", notes = "仅待收货订单可以确认收货")
    @PostMapping("/{id}/received")
    public Result<Void> received(@PathVariable("id") Long id, @Validated @RequestBody ReceivedOrderClientRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.receivedByBuyer(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("订单详情")
    @GetMapping("/{id}/complex")
    public Result<OrderComplexVo> getOrder(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @ApiOperation("配送单")
    @GetMapping("/{id}/delivery/complex")
    public Result<OrderDeliveryComplexVo> getDelivery(@PathVariable("id") Long id) {
        return Result.success(service.getDeliveryByBuyer(id));
    }

}
