package in.hocg.rabbit.chaos.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ChaosServiceName.NAME)
public interface EmailServiceApi {
    String CONTEXT_ID = "EmailServiceApi";

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
