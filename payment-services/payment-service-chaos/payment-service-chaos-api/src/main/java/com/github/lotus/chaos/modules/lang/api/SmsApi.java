package com.github.lotus.chaos.modules.lang.api;

import com.github.lotus.chaos.ServiceName;
import com.github.lotus.chaos.modules.ums.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = AccountApi.CONTEXT_ID)
public interface SmsApi {
    String CONTEXT_ID = "SmsApi";

    @GetMapping(CONTEXT_ID + "/validSmsCode")
    boolean validSmsCode(@RequestParam("phone") String phone,
                         @RequestParam("smsCode") String smsCode);
}
