package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.GameRoom;
import in.hocg.rabbit.mina.biz.pojo.ro.JoinRoomRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RoomPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [小程序模块] 游戏房间表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
public interface GameRoomService extends AbstractService<GameRoom> {

    GameRoomComplexVo createAndGet(MinaGameCreateRoomRo ro);

    GameRoomComplexVo getComplexByEncoding(String encoding);

    void joinUser(JoinRoomRo ro);

    IPage<GameRoomOrdinaryVo> paging(RoomPagingRo ro);
}
