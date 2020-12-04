package com.github.lotus.chaos.api.modules.ums.api;

import com.github.lotus.chaos.api.ServiceName;
import com.github.lotus.chaos.api.modules.ums.api.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.api.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
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
    void createAccount(@Validated @RequestBody CreateAccountRo ro);

}