package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.GameCard;
import com.github.lotus.mina.biz.pojo.ro.GameCardSaveRo;
import com.github.lotus.mina.biz.pojo.vo.GameCardComplexVo;
import com.github.lotus.mina.biz.pojo.vo.MinaGameCardComplexVo;
import com.github.lotus.mina.biz.pojo.vo.GameCardOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface GameCardMapping {

    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "viewUrls", ignore = true)
    MinaGameCardComplexVo asGameComplexVo(GameCard entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    GameCard asGameCard(GameCardSaveRo ro);

    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    GameCardOrdinaryVo asOrdinary(GameCard entity);

    @Mapping(target = "viewUrls", ignore = true)
    @Mapping(target = "mainViewUrl", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    GameCardComplexVo asComplex(GameCard entity);
}
