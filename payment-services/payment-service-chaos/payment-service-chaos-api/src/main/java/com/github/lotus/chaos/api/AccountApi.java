package com.github.lotus.chaos.api;

import com.github.lotus.chaos.ServiceName;
import com.github.lotus.chaos.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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
}
