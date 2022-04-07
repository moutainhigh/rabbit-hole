package in.hocg.rabbit.mina.api;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.api.pojo.ro.BaseRechargeRo;
import in.hocg.rabbit.mina.api.pojo.ro.QueryRechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = MinaServiceName.NAME)
public interface RechargeServiceApi {
    String CONTEXT_ID = "RechargeServiceApi";

    @PostMapping(value = CONTEXT_ID + "/recharge")
    Result<RechargeOrderVo> recharge(@RequestBody RechargeOrderRo ro);

    @PostMapping(value = CONTEXT_ID + "/queryRecharge")
    Result<RechargeOrderVo> queryRecharge(@RequestBody QueryRechargeOrderRo ro);

    @PostMapping(value = CONTEXT_ID + "/listProduct")
    Result<List<RechargeProductVo>> listProduct(@RequestBody BaseRechargeRo ro);
}
