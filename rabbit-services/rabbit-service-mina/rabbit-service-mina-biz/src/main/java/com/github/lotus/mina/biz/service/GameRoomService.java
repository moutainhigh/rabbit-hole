package com.github.lotus.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.GameRoom;
import com.github.lotus.mina.biz.pojo.ro.JoinRoomRo;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import com.github.lotus.mina.biz.pojo.ro.RoomPagingRo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomComplexVo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomOrdinaryVo;
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
