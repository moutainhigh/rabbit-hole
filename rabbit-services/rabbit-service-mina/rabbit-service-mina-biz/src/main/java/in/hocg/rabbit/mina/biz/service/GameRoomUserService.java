package in.hocg.rabbit.mina.biz.service;

import in.hocg.rabbit.mina.biz.entity.GameRoomUser;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [小程序模块] 游戏房间用户表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
public interface GameRoomUserService extends AbstractService<GameRoomUser> {

    List<GameRoomUser> listByRoomId(Long roomId);

    void removeByRoomId(Long roomId);

    void removeByUserFlag(String userFlag);
}
