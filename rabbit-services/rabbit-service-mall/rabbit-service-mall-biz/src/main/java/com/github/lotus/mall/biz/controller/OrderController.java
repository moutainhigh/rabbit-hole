package com.github.lotus.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.pojo.ro.*;
import com.github.lotus.mall.biz.pojo.vo.OrderComplexVo;
import com.github.lotus.mall.biz.service.OrderService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
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
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @UseLogger("分页查询 - 订单列表")
    @PostMapping("/_paging")
    public Result<IPage<OrderComplexVo>> paging(@Validated @RequestBody OrderPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger("获取 - 订单详情")
    @GetMapping("/{id}/complex")
    public Result<OrderComplexVo> getComplex(@PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger("关闭 - 订单")
    @PostMapping("/{id}/close")
    public Result<Void> close(@PathVariable Long id, @Validated @RequestBody CloseOrderRo ro) {
        service.close(id, ro);
        return Result.success();
    }

    @UseLogger("发货 - 订单")
    @PostMapping("/{id}/shipped")
    public Result<Void> shipped(@PathVariable Long id, @Validated @RequestBody ShippedOrderRo ro) {
        service.shipped(id, ro);
        return Result.success();
    }

    @UseLogger("修改 - 订单")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@PathVariable Long id, @Validated @RequestBody UpdateOrderRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger("删除 - 订单")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @UseLogger("通知订单状态 - 订单")
    @PostMapping("/pay/result")
    public String payResult(@RequestBody OrderPayResultRo ro) {
        service.payResult(ro);
        return "notify_success";
    }
}

