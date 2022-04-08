package in.hocg.rabbit.mina.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.mina.api.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeOrderPageRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeProductCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeAccountVo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderOrdinaryVo;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Validated
@RestController
@Api(tags = "mina::充值")
@RequestMapping("/recharge")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeOrderController {
    private final RechargeOrderService service;

    @ApiOperation("获取充值账户")
    @GetMapping("/account")
    public Result<RechargeAccountVo> account() {
        return Result.success(service.getAccount(UserContextHolder.getUserIdThrow()));
    }

    @ApiOperation("获取充值订单列表")
    @PostMapping("/order/_paging")
    public Result<IPage<RechargeOrderOrdinaryVo>> paging(@RequestBody RechargeOrderPageRo ro) {
        ro.setOpsUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.paging(ro));
    }

    @ApiOperation("充值")
    @PostMapping("/order")
    public Result<Void> recharge(@RequestBody RechargeOrderRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.recharge(ro);
        return Result.success();
    }

    @ApiOperation("充值产品")
    @PostMapping("/product/_complete")
    public Result<List<KeyValue>> complete(@RequestBody RechargeProductCompleteRo ro) {
        ro.setOpsUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.completeWithProduct(ro));
    }

    @ApiOperation("触发通知")
    @PostMapping("/trigger/notify/{orderId}")
    public Result<Void> triggerNotify(@PathVariable("orderId") Long id) {
        service.notifyAccess(id, UserContextHolder.getUserIdThrow());
        return Result.success();
    }
}
