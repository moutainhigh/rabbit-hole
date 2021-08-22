package com.github.lotus.ums.api;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.Optional;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface UserAddressServiceApi {
    String CONTEXT_ID = "UserAddressServiceApi";

}
