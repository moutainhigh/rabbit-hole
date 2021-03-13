package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.GameRoom;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/3/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface GameRoomMapping {
    GameRoom asGameRoom(MinaGameCreateRoomRo ro);

    GameRoomComplexVo asComplex(GameRoom entity);
}
