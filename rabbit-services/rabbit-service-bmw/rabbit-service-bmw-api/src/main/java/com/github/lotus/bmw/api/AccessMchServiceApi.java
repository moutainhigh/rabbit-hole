package com.github.lotus.bmw.api;

import com.github.lotus.bmw.api.pojo.vo.AccessMchComplexVo;
import com.github.lotus.common.constant.GlobalConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Created by hocgin on 2021/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface AccessMchServiceApi {
    String CONTEXT_ID = "AccessMchServiceApi";

    @PostMapping(value = CONTEXT_ID + "/listComplexById", headers = GlobalConstant.FEIGN_HEADER)
    List<AccessMchComplexVo> listComplexById(@RequestParam("id") Collection<Long> id);
}
