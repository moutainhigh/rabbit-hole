package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.api.pojo.ro.BaseRechargeRo;
import in.hocg.rabbit.mina.api.pojo.ro.QueryRechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.constant.MinaConstant;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
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
        RechargeHelper.checkThrow(ro, ro.getUserId(), ro.getSign());
        return Result.success(rechargeOrderService.recharge(ro));
    }

    @ApiOperation("充值结果")
    @PostMapping("/recharge/result")
    public Result<RechargeOrderVo> queryRecharge(@RequestBody QueryRechargeOrderRo ro) {
        RechargeHelper.checkThrow(ro, ro.getUserId(), ro.getSign());
        return Result.success(rechargeOrderService.queryRecharge(ro));
    }

    @ApiOperation("充值产品列表")
    @PostMapping("/recharge/product")
    public Result<List<RechargeProductVo>> listProduct(@RequestBody BaseRechargeRo ro) {
        RechargeHelper.checkThrow(ro, ro.getUserId(), ro.getSign());
        return Result.success(rechargeOrderService.listProduct(ro.getUserId()));
    }
}
