package in.hocg.rabbit.bmw.biz.controller;

import in.hocg.rabbit.bmw.biz.pojo.ro.CloseTradeRo;
import in.hocg.rabbit.bmw.biz.pojo.ro.GoPayRo;
import in.hocg.rabbit.bmw.biz.pojo.vo.CashierInfoVo;
import in.hocg.rabbit.bmw.biz.pojo.vo.GoPayVo;
import in.hocg.rabbit.bmw.biz.service.BmwService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"bmw::收银台"})
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping
public class BmwController {
    private final BmwService bmwService;

    @ResponseBody
    @ApiOperation("前往第三方支付收银台")
    @GetMapping("/go-cashier")
    public void goCashier(@RequestParam("key") String key, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type", "text/html;charset=utf-8");
        response.getWriter().write(bmwService.getCashierPage(key));
    }

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
