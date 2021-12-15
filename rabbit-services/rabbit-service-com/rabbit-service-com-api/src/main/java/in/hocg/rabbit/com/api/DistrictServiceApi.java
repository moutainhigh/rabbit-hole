package in.hocg.rabbit.com.api;

import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
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
@FeignClient(value = ComServiceName.NAME)
public interface DistrictServiceApi {
    String CONTEXT_ID = "DistrictServiceApi";

    @PostMapping(value = CONTEXT_ID + "/listProvince", headers = ComServiceName.FEIGN_HEADER)
    List<DistrictComplexVo> listProvince(@RequestParam("adcode") List<String> adcode);

    @PostMapping(value = CONTEXT_ID + "/listCity", headers = ComServiceName.FEIGN_HEADER)
    List<DistrictComplexVo> listCity(@RequestParam("adcode") List<String> adcode);

    @PostMapping(value = CONTEXT_ID + "/listDistrict", headers = ComServiceName.FEIGN_HEADER)
    List<DistrictComplexVo> listDistrict(@RequestParam("adcode") List<String> adcode);

    @PostMapping(value = CONTEXT_ID + "/getCityByCityCode", headers = ComServiceName.FEIGN_HEADER)
    DistrictComplexVo getComplexByCityCode(@RequestParam("cityCode") String cityCode);

    @PostMapping(value = CONTEXT_ID + "/getById", headers = ComServiceName.FEIGN_HEADER)
    DistrictComplexVo getComplexById(@RequestParam("id") Long id);
}
