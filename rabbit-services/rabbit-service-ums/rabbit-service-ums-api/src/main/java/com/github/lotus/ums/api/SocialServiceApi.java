package com.github.lotus.ums.api;

import com.github.lotus.ums.api.pojo.ro.InsertSocialRo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = SocialServiceApi.CONTEXT_ID)
public interface SocialServiceApi {
    String CONTEXT_ID = "SocialServiceApi";

    @GetMapping(CONTEXT_ID + "/getAccountBySocialTypeAndSocialId")
    UserDetailVo getAccountBySocialTypeAndSocialId(@RequestParam("socialType") String socialType,
                                                   @RequestParam("socialId") String socialId);

    @PostMapping(CONTEXT_ID + "/insertOne")
    void insertOne(@Validated @RequestBody InsertSocialRo ro);
}
