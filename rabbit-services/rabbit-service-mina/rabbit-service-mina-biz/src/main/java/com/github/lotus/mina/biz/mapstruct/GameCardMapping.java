package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.GameCard;
import com.github.lotus.mina.biz.pojo.vo.GameComplexVo;
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
    GameComplexVo asGameComplexVo(GameCard entity);
}
