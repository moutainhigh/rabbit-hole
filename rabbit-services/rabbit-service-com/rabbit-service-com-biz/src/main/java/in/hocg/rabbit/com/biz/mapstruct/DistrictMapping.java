package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.District;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictTreeVo;
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
