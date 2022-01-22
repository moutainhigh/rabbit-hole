package in.hocg.rabbit.mall.biz.controller.buyer;


import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mall.biz.pojo.ro.CloseByBuyerRo;
import in.hocg.rabbit.mall.biz.service.OrderMaintainService;
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
@Api(tags = {"mall::订单售后(买家)", "买家", "mall"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/buyer/order-maintain")
public class OrderMaintainBuyerController {
    private final OrderMaintainService service;

    @UseLogger
    @ApiOperation("关闭售后 - 订单售后")
    @PostMapping("/{id}/close")
    public Result<Void> close(@PathVariable Long id, @Validated @RequestBody CloseByBuyerRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.closeByBuyer(id, ro);
        return Result.success();
    }

}

