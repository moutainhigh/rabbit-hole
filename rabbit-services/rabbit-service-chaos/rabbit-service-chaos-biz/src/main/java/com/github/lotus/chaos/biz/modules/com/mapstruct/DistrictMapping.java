package com.github.lotus.chaos.biz.modules.com.mapstruct;

import com.github.lotus.chaos.biz.modules.com.entity.District;
import com.github.lotus.chaos.biz.modules.com.pojo.vo.district.DistrictComplexVo;
import com.github.lotus.chaos.biz.modules.com.pojo.vo.district.DistrictTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/2/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface DistrictMapping {

    @Mapping(target = "children", ignore = true)
    DistrictTreeVo asDistrictTreeVo(District entity);

    DistrictComplexVo asDistrictComplexVo(District entity);
}
