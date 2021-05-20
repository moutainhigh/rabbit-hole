package com.github.lotus.pay.api;

import com.github.lotus.pay.api.pojo.vo.AccessAppOrdinaryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface AccessAppServiceApi {
    String CONTEXT_ID = "AccessAppServiceApi";

    @PostMapping(CONTEXT_ID + "/listOrdinaryById")
    List<AccessAppOrdinaryVo> listOrdinaryById(@RequestParam("id") List<Long> id);
}
