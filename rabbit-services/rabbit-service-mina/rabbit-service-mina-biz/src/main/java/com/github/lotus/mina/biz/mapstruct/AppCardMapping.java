package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.AppCard;
import com.github.lotus.mina.biz.pojo.ro.AppCardSaveRo;
import com.github.lotus.mina.biz.pojo.vo.AppCardComplexVo;
import com.github.lotus.mina.biz.pojo.vo.AppCardOrdinaryVo;
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
    AppCardComplexVo asAppComplexVo(AppCard entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AppCard asAppCard(AppCardSaveRo ro);

    AppCardOrdinaryVo asOrdinary(AppCard entity);
}
