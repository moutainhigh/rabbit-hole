package in.hocg.rabbit.mall.biz.controller.seller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderMaintainPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.PassOrderMaintainRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderRefundApplyComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderMaintainService;
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
 * 订单售后 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-16
 */
@Api(tags = {"mall::订单售后(卖家)", "卖家", "mall"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/seller/order-maintain")
public class OrderMaintainSellerController {
    private final OrderMaintainService service;

    @UseLogger
    @ApiOperation("分页查询 - 订单售后")
    @PostMapping("/_paging")
    public Result<IPage<OrderRefundApplyComplexVo>> paging(@Validated @RequestBody OrderMaintainPagingRo ro) {
        return Result.success();
    }

    @UseLogger
    @ApiOperation("获取 - 订单售后")
    @GetMapping("/{id}/complex")
    public Result<OrderRefundApplyComplexVo> getComplex(@PathVariable Long id) {
        return Result.success();
    }

    @UseLogger
    @ApiOperation("通过 - 退费申请")
    @PostMapping("/{id}/pass")
    public Result<Void> pass(@PathVariable Long id, @Validated @RequestBody PassOrderMaintainRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.pass(id, ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("关闭 - 退费申请")
    @PostMapping("/{id}/close")
    public Result<Void> close(@PathVariable Long id) {
        service.closeBySeller(id);
        return Result.success();
    }

}

