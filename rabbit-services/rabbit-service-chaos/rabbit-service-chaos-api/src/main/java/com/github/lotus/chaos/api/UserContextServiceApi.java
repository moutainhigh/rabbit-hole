package com.github.lotus.chaos.api;

import com.github.lotus.common.constant.GlobalConstant;
import com.github.lotus.usercontext.ifc.UserContextService;
import com.github.lotus.usercontext.ifc.vo.UserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/7/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface UserContextServiceApi extends UserContextService {
    String CONTEXT_ID = "UserContextServiceApi";

    @Override
    @PostMapping(value = CONTEXT_ID + "/getUserDetail", headers = GlobalConstant.FEIGN_HEADER)
    UserDetail getUserDetail(@RequestParam("username") String username);
}
