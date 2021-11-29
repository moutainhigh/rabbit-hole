package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.GameRoom;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomOrdinaryVo;
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
