package com.github.lotus.com.biz.apiimpl;

import com.github.lotus.com.api.DistrictServiceApi;
import com.github.lotus.com.api.pojo.vo.DistrictComplexVo;
import com.github.lotus.com.biz.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DistrictServiceApiImpl implements DistrictServiceApi {
    private final DistrictService service;

    @Override
    public List<DistrictComplexVo> listProvince(List<String> adcode) {
        return service.listProvince(adcode);
    }

    @Override
    public List<DistrictComplexVo> listCity(List<String> adcode) {
        return service.listCity(adcode);
    }

    @Override
    public List<DistrictComplexVo> listDistrict(List<String> adcode) {
        return service.listDistrict(adcode);
    }

    @Override
    public DistrictComplexVo getComplexByCityCode(String cityCode) {
        return service.getComplexByCityCode(cityCode);
    }

    @Override
    public DistrictComplexVo getComplexById(Long id) {
        return service.getComplexById(id);
    }
}
