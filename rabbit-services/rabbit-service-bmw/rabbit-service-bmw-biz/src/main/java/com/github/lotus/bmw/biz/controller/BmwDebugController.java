package com.github.lotus.bmw.biz.controller;

import com.github.lotus.bmw.api.BmwServiceApi;
import com.github.lotus.bmw.api.pojo.ro.CreateTradeRo;
import com.github.lotus.bmw.api.pojo.ro.GetCashierRo;
import com.github.lotus.bmw.api.pojo.vo.TradeStatusSyncVo;
import in.hocg.boot.web.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/debug")
public class BmwDebugController {
    private final BmwServiceApi bmwServiceApi;

    @ResponseBody
    @PostMapping("/createAndGoPay")
    public Result<String> createAndGoPay(@RequestParam("sceneCode") String sceneCode, @RequestBody CreateTradeRo ro) {
        bmwServiceApi.createTrade(ro);
        GetCashierRo getCashierRo = new GetCashierRo();
        getCashierRo.setOutTradeNo(ro.getOutTradeNo());
        getCashierRo.setAccessCode(ro.getAccessCode());
        getCashierRo.setSceneCode(sceneCode);
        return Result.success(bmwServiceApi.getCashierUrl(getCashierRo));
    }
}
