package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.constant.MinaConstant;
import in.hocg.rabbit.mina.biz.pojo.vo.ShareContentVo;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Validated
@RestController
@Api(tags = "mina::充值回调通知")
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeController {
    private final RechargeOrderService service;

    @PostMapping(MinaConstant.RECHARGE_CALLBACK_URI)
    @ApiOperation("充值回调通知")
    public String rechargeNotify(@PathVariable("orderNo") String orderNo) {
        service.rechargeNotify(orderNo);
        return "success";
    }
}
