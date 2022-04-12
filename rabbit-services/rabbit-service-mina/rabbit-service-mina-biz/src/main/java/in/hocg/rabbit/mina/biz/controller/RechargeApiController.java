package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.pojo.ro.BaseRechargeRo;
import in.hocg.rabbit.mina.biz.pojo.ro.QueryRechargeOrderRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@RestController
@Api(tags = "mina::充值对外接口")
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class RechargeApiController {
    private final RechargeOrderService rechargeOrderService;

    @ApiOperation("充值操作")
    @PostMapping("/recharge")
    public Result<RechargeOrderVo> recharge(@RequestBody RechargeOrderRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(rechargeOrderService.recharge(ro));
    }

    @ApiOperation("充值结果")
    @PostMapping("/recharge/result")
    public Result<RechargeOrderVo> queryRecharge(@RequestBody QueryRechargeOrderRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(rechargeOrderService.queryRecharge(ro));
    }

    @ApiOperation("充值产品列表")
    @PostMapping("/recharge/product")
    public Result<List<RechargeProductVo>> listProduct(@RequestBody BaseRechargeRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(rechargeOrderService.listProduct(ro.getUserId()));
    }
}
