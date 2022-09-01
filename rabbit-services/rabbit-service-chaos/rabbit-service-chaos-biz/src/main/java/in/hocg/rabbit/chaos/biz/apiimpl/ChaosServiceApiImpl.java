package in.hocg.rabbit.chaos.biz.apiimpl;

import in.hocg.rabbit.chaos.api.ChaosServiceApi;
import in.hocg.rabbit.chaos.api.pojo.ro.SendVerifyCodeRo;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import in.hocg.rabbit.chaos.api.pojo.vo.GetVerifyCodeVo;
import in.hocg.rabbit.chaos.biz.service.ChaosService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ChaosServiceApiImpl implements ChaosServiceApi {
    private final ChaosService service;


    @Override
    public ValidVerifyCodeVo validVerifyCode(String serialNo, String verifyCode) {
        return service.validVerifyCode(serialNo, verifyCode);
    }

    @Override
    public GetVerifyCodeVo sendVerifyCode(SendVerifyCodeRo ro) {
        return service.sendVerifyCode(ro);
    }
}
