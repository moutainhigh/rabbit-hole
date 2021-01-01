package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.AppCard;
import com.github.lotus.mina.biz.pojo.vo.AppComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AppCardMapping {

    @Mapping(target = "viewUrls", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "href", ignore = true)
    AppComplexVo asAppComplexVo(AppCard entity);
}
