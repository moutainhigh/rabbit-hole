package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.biz.entity.Api;
import com.github.lotus.ums.biz.pojo.ro.ApiSaveRo;
import com.github.lotus.ums.biz.pojo.vo.ApiComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ApiMapping {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Api asApi(ApiSaveRo ro);

    ApiComplexVo asComplex(Api entity);
}
