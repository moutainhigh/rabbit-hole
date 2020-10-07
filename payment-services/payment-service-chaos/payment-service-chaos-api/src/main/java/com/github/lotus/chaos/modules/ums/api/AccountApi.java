package com.github.lotus.chaos.modules.ums.api;

import com.github.lotus.chaos.ServiceName;
import com.github.lotus.chaos.modules.ums.ro.CreateAccountRo;
import com.github.lotus.chaos.modules.ums.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = AccountApi.CONTEXT_ID)
public interface AccountApi {
    String CONTEXT_ID = "AccountApi";

    @GetMapping(CONTEXT_ID + "/getUser")
    UserDetailVo getUser(@RequestParam("username") String username);

    @PostMapping(CONTEXT_ID + "/createAccount")
    void createAccount(@RequestBody CreateAccountRo ro);
}
