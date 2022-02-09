package in.hocg.rabbit.mall.biz.controller.seller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * [订单模块] 订单主表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-14
 */
@Api(tags = {"mall::订单(卖家)", "卖家", "mall"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/seller/order")
public class OrderSellerController {
    private final OrderService service;

    @UseLogger
    @ApiOperation("分页查询 - 订单列表")
    @PostMapping("/_paging")
    public Result<IPage<OrderOrdinaryVo>> paging(@Validated @RequestBody OrderPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @ApiOperation("获取 - 订单详情")
    @GetMapping("/{id}/complex")
    public Result<OrderComplexVo> getComplex(@PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @ApiOperation(value = "关闭 - 订单", notes = "关闭带支付订单")
    @PostMapping("/{id}/close")
    public Result<Void> close(@PathVariable Long id, @Validated @RequestBody CloseOrderManageRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.closeBySeller(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation(value = "退款 - 订单", notes = "退款待发货订单")
    @PostMapping("/{id}/refund")
    public Result<Void> refund(@PathVariable Long id, @Validated @RequestBody RefundOrderManageRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.refundBySeller(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("发货 - 订单")
    @PostMapping("/{id}/shipped")
    public Result<Void> shipped(@PathVariable Long id, @Validated @RequestBody ShippedOrderBySellerRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.shipped(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("手动支付 - 订单")
    @PostMapping("/{id}/payed")
    public Result<Void> shipped(@PathVariable Long id, @Validated @RequestBody PayedOrderBySellerRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.payed(id, ro);
        return Result.success();
    }


    @UseLogger
    @ApiOperation("后台调价 - 订单")
    @PostMapping("/{id}/adjustment")
    public Result<Void> adjustment(@PathVariable Long id, @Validated @RequestBody AdjustmentOrderBySellerRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.adjustment(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("修改 - 订单")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@PathVariable Long id, @Validated @RequestBody UpdateOrderRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("删除 - 订单")
    @DeleteMapping
    public Result<Void> delete(@Validated @RequestBody DeleteRo ro) {
        service.delete(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("通知订单状态 - 订单")
    @PostMapping("/pay/result")
    public String payResult(@RequestBody OrderPayResultRo ro) {
        service.payResult(ro);
        return "notify_success";
    }
}

