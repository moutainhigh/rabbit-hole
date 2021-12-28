package in.hocg.rabbit.sso.controller;

import in.hocg.boot.web.result.Result;
import in.hocg.rabbit.docking.api.pojo.vo.WxMpQrCodeVo;
import in.hocg.rabbit.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.rabbit.sso.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/12/28
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "sso::静态文件")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LoginController {
    private final IndexService service;

    @ApiOperation(value = "微信公众号二维码", notes = "免登陆")
    @GetMapping("/wechat/qrcode")
    public Result<WxMpQrCodeVo> getWechatQrcode() {
        return Result.success(service.getWechatQrcode());
    }

    @ApiOperation(value = "获取微信登陆状态", notes = "免登陆")
    @GetMapping("/wechat/{idFlag}/qrcode")
    public Result<WxLoginStatusVo> getWechatQrcodeStatus(@PathVariable("idFlag") String idFlag,
                                                         @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        return Result.success(service.getWechatQrcodeStatus(idFlag, redirectUrl));
    }
}
