package com.github.lotus.chaos.api.modules.ums;

import com.github.lotus.chaos.api.ServiceName;
import com.github.lotus.chaos.api.modules.ums.pojo.ro.CreateAccountRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
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
@FeignClient(value = ServiceName.NAME, contextId = AccountServiceApi.CONTEXT_ID)
public interface AccountServiceApi {
    String CONTEXT_ID = "AccountServiceApi";

    @GetMapping(CONTEXT_ID + "/getUserByUsername")
    UserDetailVo getUserByUsername(@RequestParam("username") String username);

    @GetMapping(CONTEXT_ID + "/getUserByUsernameOrEmailOrPhone")
    UserDetailVo getUserByUsernameOrEmailOrPhone(@RequestParam("unique") String unique);

    @GetMapping(CONTEXT_ID + "/getUserByPhone")
    UserDetailVo getUserByPhone(@RequestParam("phone") String phone);

    @PostMapping(CONTEXT_ID + "/createAccount")
    UserDetailVo createAccount(@Validated @RequestBody CreateAccountRo ro);

    @PostMapping(CONTEXT_ID + "/getUserToken")
    String getUserToken(@RequestParam("username") String username);

    @PostMapping(CONTEXT_ID + "/getUsername")
    String getUsername(@RequestParam("token") String token);
}
