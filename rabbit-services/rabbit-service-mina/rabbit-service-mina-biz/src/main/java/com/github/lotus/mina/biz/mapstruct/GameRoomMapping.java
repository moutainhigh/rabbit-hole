package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.GameRoom;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomComplexVo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/3/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface GameRoomMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    GameRoom asGameRoom(MinaGameCreateRoomRo ro);

    @Mapping(target = "players", ignore = true)
    GameRoomComplexVo asComplex(GameRoom entity);

    @Mapping(target = "creatorName", ignore = true)
    GameRoomOrdinaryVo asOrdinary(GameRoom entity);
}
