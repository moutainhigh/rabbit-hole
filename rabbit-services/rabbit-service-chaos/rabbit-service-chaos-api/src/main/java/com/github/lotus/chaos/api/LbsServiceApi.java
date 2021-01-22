package com.github.lotus.chaos.api;

import com.github.lotus.chaos.api.pojo.vo.AMapDistrictVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = LbsServiceApi.CONTEXT_ID)
public interface LbsServiceApi {
    String CONTEXT_ID = "LbsServiceApi";

    @PostMapping(CONTEXT_ID + "/listDistrict")
    List<AMapDistrictVo> listDistrict();
}
