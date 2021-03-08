package com.github.lotus.mina.biz.service.impl;

import com.github.lotus.mina.biz.entity.GameRoomUser;
import com.github.lotus.mina.biz.mapper.GameRoomUserMapper;
import com.github.lotus.mina.biz.service.GameRoomUserService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * [小程序模块] 游戏房间用户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GameRoomUserServiceImpl extends AbstractServiceImpl<GameRoomUserMapper, GameRoomUser>
    implements GameRoomUserService {

    @Override
    public List<GameRoomUser> listByRoomId(Long roomId) {
        return lambdaQuery().eq(GameRoomUser::getRoomId, roomId).list();
    }

    @Override
    public void removeByRoomId(Long roomId) {
        lambdaUpdate().eq(GameRoomUser::getRoomId, roomId).remove();
    }
}
