package in.hocg.rabbit.mina.biz.apiimpl;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.api.RechargeServiceApi;
import in.hocg.rabbit.mina.api.pojo.ro.BaseRechargeRo;
import in.hocg.rabbit.mina.api.pojo.ro.QueryRechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
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
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class RechargeServiceApiImpl implements RechargeServiceApi {
    private final RechargeOrderService rechargeOrderService;

    @Override
    public Result<RechargeOrderVo> recharge(RechargeOrderRo ro) {
        RechargeHelper.checkThrow(ro, ro.getUserId(), ro.getSign());
        return Result.success(rechargeOrderService.recharge(ro));
    }

    @Override
    public Result<RechargeOrderVo> queryRecharge(QueryRechargeOrderRo ro) {
        RechargeHelper.checkThrow(ro, ro.getUserId(), ro.getSign());
        return Result.success(rechargeOrderService.queryRecharge(ro));
    }

    @Override
    public Result<List<RechargeProductVo>> listProduct(BaseRechargeRo ro) {
        RechargeHelper.checkThrow(ro, ro.getUserId(), ro.getSign());
        return Result.success(rechargeOrderService.listProduct(ro.getUserId()));
    }
}
