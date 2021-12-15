package in.hocg.rabbit.chaos.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ChaosServiceName.NAME)
public interface SmsServiceApi {
    String CONTEXT_ID = "SmsServiceApi";

    /**
     * 验证验证码
     *
     * @param phone
     * @param smsCode
     * @return
     */
    @PostMapping(value = CONTEXT_ID + "/validVerifyCode", headers = ChaosServiceName.FEIGN_HEADER)
    boolean validVerifyCode(@RequestParam("phone") String phone, @RequestParam("smsCode") String smsCode);

}
