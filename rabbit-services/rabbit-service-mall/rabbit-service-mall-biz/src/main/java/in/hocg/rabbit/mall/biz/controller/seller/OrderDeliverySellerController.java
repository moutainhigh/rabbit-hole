package in.hocg.rabbit.mall.biz.controller.seller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderDeliveryPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderMaintainPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.PassOrderMaintainRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainComplexVo;
import in.hocg.rabbit.mall.biz.service.OrderDeliveryService;
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
 * 配送单 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-03-16
 */
@Api(tags = {"mall::配送单(卖家)", "卖家", "mall"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/seller/order-delivery")
public class OrderDeliverySellerController {
    private final OrderDeliveryService service;

    @UseLogger
    @ApiOperation("分页查询 - 配送单")
    @PostMapping("/_paging")
    public Result<IPage<OrderDeliveryOrdinaryVo>> paging(@Validated @RequestBody OrderDeliveryPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @ApiOperation("获取 - 配送单")
    @GetMapping("/{id}/complex")
    public Result<OrderDeliveryComplexVo> getComplex(@PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }


}

