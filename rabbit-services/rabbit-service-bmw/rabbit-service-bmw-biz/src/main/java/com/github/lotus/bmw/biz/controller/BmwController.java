package com.github.lotus.bmw.biz.controller;

import com.github.lotus.bmw.biz.pojo.ro.CloseTradeRo;
import com.github.lotus.bmw.biz.pojo.ro.GoPayRo;
import com.github.lotus.bmw.biz.pojo.vo.CashierInfoVo;
import com.github.lotus.bmw.biz.pojo.vo.GoPayVo;
import com.github.lotus.bmw.biz.service.BmwService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping
public class BmwController {
    private final BmwService bmwService;

    @ResponseBody
    @ApiOperation("获取收银台信息")
    @GetMapping("/cashier")
    public Result<CashierInfoVo> getCashierInfo(@RequestParam("u") String u) {
        return Result.success(bmwService.getCashierInfo(u));
    }

    @ResponseBody
    @ApiOperation("发起支付")
    @PostMapping("/go-pay")
    public Result<GoPayVo> goPay(@Validated @RequestBody GoPayRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        ro.setClientIp(SpringServletContext.getClientIp().orElse(null));
        return Result.success(bmwService.goPay(ro));
    }

    @ResponseBody
    @ApiOperation("关单")
    @PostMapping("/close-trade")
    public Result<Void> closeTrade(@Validated @RequestBody CloseTradeRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        ro.setClientIp(SpringServletContext.getClientIp().orElse(null));
        bmwService.closeTrade(ro);
        return Result.success();
    }
}
