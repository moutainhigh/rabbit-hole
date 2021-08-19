package com.github.lotus.chaos.api;

import com.github.lotus.chaos.api.pojo.vo.AMapDistrictVo;
import com.github.lotus.common.constant.GlobalConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface LbsServiceApi {
    String CONTEXT_ID = "LbsServiceApi";

    @PostMapping(value = CONTEXT_ID + "/listDistrict", headers = GlobalConstant.FEIGN_HEADER)
    List<AMapDistrictVo> listDistrict();
}
