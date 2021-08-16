package com.github.lotus.bmw.biz.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
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
    @GetMapping("/cashier")
    @ApiOperation("获取收银台信息")
    public Result<CashierInfoVo> getCashierInfo(@RequestParam("u") String u) {
        return Result.success(bmwService.getCashierInfo(u));
    }

    @ResponseBody
    @GetMapping("/go-pay")
    @ApiOperation("发起支付")
    public Result<GoPayVo> goPay(@Validated @RequestBody GoPayRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        ro.setClientIp(SpringServletContext.getClientIp().orElse(null));
        return Result.success(bmwService.goPay(ro));
    }
}
