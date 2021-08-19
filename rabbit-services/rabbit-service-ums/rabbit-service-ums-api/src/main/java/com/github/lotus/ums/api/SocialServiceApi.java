package com.github.lotus.ums.api;

import com.github.lotus.common.constant.GlobalConstant;
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
@FeignClient(value = ServiceName.NAME)
public interface SocialServiceApi {
    String CONTEXT_ID = "SocialServiceApi";

    @GetMapping(value = CONTEXT_ID + "/getAccountBySocialTypeAndSocialId", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo getAccountBySocialTypeAndSocialId(@RequestParam("socialType") String socialType, @RequestParam("socialId") String socialId);

    @PostMapping(value = CONTEXT_ID + "/insertOne", headers = GlobalConstant.FEIGN_HEADER)
    void insertOne(@Validated @RequestBody InsertSocialRo ro);
}
