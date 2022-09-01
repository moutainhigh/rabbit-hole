package in.hocg.rabbit.chaos.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ChaosServiceName.NAME)
public interface ChaosServiceApi {
    String CONTEXT_ID = "ChaosServiceApi";

    /**
     * 发送验证码
     *
     * @param email
     * @param verifyCode
     * @return
     */
    @PostMapping(value = CONTEXT_ID + "/validVerifyCode", headers = ChaosServiceName.FEIGN_HEADER)
    boolean validVerifyCode(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode);

    /**
     * 验证验证码
     *
     * @param email
     */
    @PostMapping(value = CONTEXT_ID + "/sendVerifyCode", headers = ChaosServiceName.FEIGN_HEADER)
    void sendVerifyCode(@RequestParam("email") String email);

}
