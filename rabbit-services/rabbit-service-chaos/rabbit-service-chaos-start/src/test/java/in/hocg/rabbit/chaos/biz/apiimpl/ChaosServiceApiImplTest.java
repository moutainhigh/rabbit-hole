package in.hocg.rabbit.chaos.biz.apiimpl;

import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.chaos.api.ChaosServiceApi;
import in.hocg.rabbit.chaos.api.pojo.ro.SendVerifyCodeRo;
import in.hocg.rabbit.chaos.api.pojo.vo.GetVerifyCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChaosServiceApiImplTest {
    @Autowired
    ChaosServiceApi chaosServiceApi;

    @Test
    void sendVerifyCode() {
        SendVerifyCodeRo ro = new SendVerifyCodeRo();
        GetVerifyCodeVo serialNo = chaosServiceApi.sendVerifyCode(ro);
        log.debug("==> {}", serialNo);
    }
}
