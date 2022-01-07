package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.api.pojo.vo.GameRoomVo;
import in.hocg.rabbit.mina.biz.entity.GameCard;
import in.hocg.rabbit.mina.biz.entity.GameRoom;
import in.hocg.rabbit.mina.biz.entity.GameRoomUser;
import in.hocg.rabbit.mina.biz.mapper.GameRoomMapper;
import in.hocg.rabbit.mina.biz.mapstruct.GameRoomMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.JoinRoomRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCreateRoomRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RoomPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameRoomOrdinaryVo;
import in.hocg.rabbit.mina.biz.service.GameCardService;
import in.hocg.rabbit.mina.biz.service.GameRoomService;
import in.hocg.rabbit.mina.biz.service.GameRoomUserService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.named.UmsNamedServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
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
    private final GameCardService gameCardService;
    private final GameRoomMapping mapping;
    private final UserServiceApi userServiceApi;

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

        Long game = ro.getGame();
        if (Objects.nonNull(game)) {
            entity.setLogoUrl(LangUtils.callIfNotNull(gameCardService.getById(game), GameCard::getLogoUrl).orElse(null));
        }

        entity.setCreatedAt(now);
        this.validInsertOrUpdate(entity);

        return this.convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinUser(JoinRoomRo ro) {
        String roomCode = ro.getRoomCode();
        String password = ro.getPassword();
        String userFlag = ro.getUserFlag();
        Long userId = ro.getUserId();
        String signalData = ro.getSignalData();
        LocalDateTime now = LocalDateTime.now();

        GameRoom entity = getByEncoding(roomCode).orElseThrow(IllegalArgumentException::new);
        // todo: 检查密码匹配

        Long roomId = entity.getId();

        gameRoomUserService.removeByUserFlag(userFlag);
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
    public IPage<GameRoomOrdinaryVo> paging(RoomPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertOrdinary);
    }

    @Override
    public GameRoomVo getRoomByRoomCode(String roomCode) {
        GameRoomComplexVo queryResult = getComplexByEncoding(roomCode);

        GameRoomVo result = new GameRoomVo();
        List<GameRoomComplexVo.PlayerUser> queryPlayers = queryResult.getPlayers();
        List<GameRoomVo.PlayerUser> players = Collections.emptyList();
        if (CollUtil.isNotEmpty(queryPlayers)) {
            Map<Long, AccountVo> userMaps = LangUtils.toMap(userServiceApi.listAccountVoById(LangUtils.toList(queryPlayers, GameRoomComplexVo.PlayerUser::getUserId)), AccountVo::getId);
            players = queryPlayers.stream().map(playerUser -> new GameRoomVo.PlayerUser()
                    .setUserFlag(playerUser.getUserFlag())
                    .setSignalData(LangUtils.callIfNotNull(userMaps.get(playerUser.getUserId()), AccountVo::getUsername).orElse(null))
                    .setUserId(playerUser.getUserId()))
                .collect(Collectors.toList());
        }
        return result.setPlayers(players);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomComplexVo getComplexByEncoding(String encoding) {
        GameRoom entity = getByEncoding(encoding).orElseThrow(IllegalArgumentException::new);
        return this.convertComplex(entity);
    }

    private GameRoomOrdinaryVo convertOrdinary(GameRoom entity) {
        return mapping.asOrdinary(entity);
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
}
