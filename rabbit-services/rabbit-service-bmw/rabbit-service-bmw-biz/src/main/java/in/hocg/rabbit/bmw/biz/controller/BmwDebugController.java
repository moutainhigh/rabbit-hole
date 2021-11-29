package in.hocg.rabbit.bmw.biz.controller;

import in.hocg.rabbit.bmw.api.BmwServiceApi;
import in.hocg.rabbit.bmw.api.pojo.ro.CreateTradeRo;
import in.hocg.rabbit.bmw.api.pojo.ro.GetCashierRo;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
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
@Api(tags = {"bmw::调试"})
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
