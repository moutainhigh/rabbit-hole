package in.hocg.rabbit.mall.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderRefundApplyPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.RefundHandleRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderRefundApplyComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderRefundApplyService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-16
 */
@Api(tags = "mall::订单退费申请")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/refund-apply")
public class OrderRefundApplyController {
    private final OrderRefundApplyService service;

    @UseLogger
    @ApiOperation("分页查询 - 订单退费申请列表")
    @PostMapping("/_paging")
    public Result<IPage<OrderRefundApplyComplexVo>> paging(@Validated @RequestBody OrderRefundApplyPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @ApiOperation("获取 - 订单退费申请详情")
    @GetMapping("/{id}/complex")
    public Result<OrderRefundApplyComplexVo> getComplex(@PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @ApiOperation("处理 - 退费申请")
    @PostMapping("/{id}/handle")
    public Result<Void> handle(@PathVariable Long id, @Validated @RequestBody RefundHandleRo ro) {
        service.handleRefund(id, ro);
        return Result.success();
    }

}

