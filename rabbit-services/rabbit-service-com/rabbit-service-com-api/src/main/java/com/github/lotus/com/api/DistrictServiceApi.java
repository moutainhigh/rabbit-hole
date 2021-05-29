package com.github.lotus.com.api;

import com.github.lotus.com.api.pojo.vo.DistrictComplexVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface DistrictServiceApi {
    String CONTEXT_ID = "DistrictServiceApi";

    @PostMapping(CONTEXT_ID + "/listProvince")
    List<DistrictComplexVo> listProvince(@RequestParam("adcode") List<String> adcode);

    @PostMapping(CONTEXT_ID + "/listCity")
    List<DistrictComplexVo> listCity(@RequestParam("adcode") List<String> adcode);

    @PostMapping(CONTEXT_ID + "/listDistrict")
    List<DistrictComplexVo> listDistrict(@RequestParam("adcode") List<String> adcode);

    @PostMapping(CONTEXT_ID + "/getCityByCityCode")
    DistrictComplexVo getComplexByCityCode(@RequestParam("cityCode") String cityCode);

    @PostMapping(CONTEXT_ID + "/getById")
    DistrictComplexVo getComplexById(@RequestParam("id") Long id);
}
