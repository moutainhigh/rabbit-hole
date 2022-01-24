package in.hocg.rabbit.mall.biz.controller.buyer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mall.biz.pojo.ro.CommentClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderDeliveryPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderDeliveryOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"mall::订单详情(买家)", "买家", "mall"})
@RestController
@RequestMapping("/buyer/order-item")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderItemBuyerController {
    private final OrderItemService service;

    @ApiOperation(value = "申请售后", notes = "交易完成可以申请售后")
    @PostMapping("/{id}/maintain")
    public Result<Void> maintain(@PathVariable("id") Long id, @Validated @RequestBody MaintainClientRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.maintainByBuyer(id, ro);
        return Result.success();
    }

    @PostMapping("/{id}/comment")
    @ApiOperation(value = "评价", notes = "交易完成可以进行评价")
    public Result<Void> comment(@PathVariable("id") Long id, @Validated @RequestBody CommentClientRo ro) {
        ro.setOperatorId(UserContextHolder.getUserIdThrow());
        service.commentByBuyer(id, ro);
        return Result.success();
    }

}
