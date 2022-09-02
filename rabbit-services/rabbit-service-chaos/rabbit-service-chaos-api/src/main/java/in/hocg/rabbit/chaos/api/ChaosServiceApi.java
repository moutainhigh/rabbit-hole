package in.hocg.rabbit.chaos.api;

import in.hocg.rabbit.chaos.api.pojo.ro.SendVerifyCodeRo;
import in.hocg.rabbit.chaos.api.pojo.vo.GetVerifyCodeVo;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hocgin
 */
@FeignClient(value = ChaosServiceName.NAME)
public interface ChaosServiceApi {
    String CONTEXT_ID = "ChaosServiceApi";

    /**
     * 验证验证码
     *
     * @param serialNo   序列号
     * @param verifyCode 验证码
     * @return 验证结果
     */
    @PostMapping(value = CONTEXT_ID + "/validVerifyCode", headers = ChaosServiceName.FEIGN_HEADER)
    ValidVerifyCodeVo validVerifyCode(@RequestParam("serialNo") String serialNo, @RequestParam("verifyCode") String verifyCode);

    /**
     * 发送验证码
     *
     * @param ro 参数
     */
    @PostMapping(value = CONTEXT_ID + "/sendVerifyCode", headers = ChaosServiceName.FEIGN_HEADER)
    GetVerifyCodeVo sendVerifyCode(@Validated @RequestBody SendVerifyCodeRo ro);

}
