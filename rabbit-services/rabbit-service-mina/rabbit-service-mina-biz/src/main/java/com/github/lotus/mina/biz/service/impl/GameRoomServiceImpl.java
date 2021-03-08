package com.github.lotus.mina.biz.service.impl;

import com.aliyun.openservices.log.http.client.ServiceException;
import com.github.lotus.mina.biz.entity.GameRoom;
import com.github.lotus.mina.biz.entity.GameRoomUser;
import com.github.lotus.mina.biz.mapper.GameRoomMapper;
import com.github.lotus.mina.biz.mapstruct.GameRoomMapping;
import com.github.lotus.mina.biz.pojo.ro.JoinRoomRo;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import com.github.lotus.mina.biz.pojo.vo.GameRoomComplexVo;
import com.github.lotus.mina.biz.service.GameRoomService;
import com.github.lotus.mina.biz.service.GameRoomUserService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * [小程序模块] 游戏房间表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GameRoomServiceImpl extends AbstractServiceImpl<GameRoomMapper, GameRoom>
    implements GameRoomService {
    private final GameRoomUserService gameRoomUserService;
    private final GameRoomMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomComplexVo createAndGet(MinaGameCreateRoomRo ro) {
        String encoding = ro.getEncoding();
        LocalDateTime now = LocalDateTime.now();

        Optional<GameRoom> gameRoomOpt = getByEncoding(encoding);
        if (gameRoomOpt.isPresent()) {
            Long roomId = gameRoomOpt.get().getId();
            removeById(roomId);
            gameRoomUserService.removeByRoomId(roomId);
        }

        GameRoom entity = mapping.asGameRoom(ro);
        entity.setCreatedAt(now);
        this.validInsertOrUpdate(entity);

        return this.convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinUser(JoinRoomRo ro) {
        String roomCode = ro.getRoomCode();
        String userFlag = ro.getUserFlag();
        Long userId = ro.getUserId();
        String signalData = ro.getSignalData();
        LocalDateTime now = LocalDateTime.now();

        GameRoom entity = getByEncoding(roomCode).orElseThrow(ServiceException::new);
        Long roomId = entity.getId();

        GameRoomUser playerUser = new GameRoomUser();
        playerUser.setRoomId(roomId);
        playerUser.setUserFlag(userFlag);
        playerUser.setUserId(userId);
        playerUser.setSignalData(signalData);
        playerUser.setCreatedAt(now);
        gameRoomUserService.validInsert(playerUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomComplexVo getComplexByEncoding(String encoding) {
        GameRoom entity = getByEncoding(encoding).orElseThrow(ServiceException::new);
        return this.convertComplex(entity);
    }

    private GameRoomComplexVo convertComplex(GameRoom entity) {
        GameRoomComplexVo result = mapping.asComplex(entity);
        Long roomId = entity.getId();

        List<GameRoomUser> gameRoomUsers = gameRoomUserService.listByRoomId(roomId);
        result.setPlayers(gameRoomUsers.stream()
            .map(item -> new GameRoomComplexVo.PlayerUser()
                .setSignalData(item.getSignalData())
                .setUserFlag(item.getUserFlag())
                .setUserId(item.getUserId()))
            .collect(Collectors.toList()));

        return result;
    }

    public Optional<GameRoom> getByEncoding(String encoding) {
        return lambdaQuery().eq(GameRoom::getEncoding, encoding).oneOpt();
    }

    private void deleteByEncoding(String encoding) {
        lambdaUpdate().eq(GameRoom::getEncoding, encoding).remove();
    }
}
