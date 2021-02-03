package com.github.lotus.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.GameCard;
import com.github.lotus.mina.biz.mapper.GameCardMapper;
import com.github.lotus.mina.biz.mapstruct.GameCardMapping;
import com.github.lotus.mina.biz.pojo.ro.GameCardCompleteRo;
import com.github.lotus.mina.biz.pojo.ro.GameCardPagingRo;
import com.github.lotus.mina.biz.pojo.ro.GameCardSaveRo;
import com.github.lotus.mina.biz.pojo.ro.MinaGameCardPagingRo;
import com.github.lotus.mina.biz.pojo.vo.GameCardComplexVo;
import com.github.lotus.mina.biz.pojo.vo.GameCardOrdinaryVo;
import com.github.lotus.mina.biz.service.GameCardService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * [小程序模块] 游戏表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GameCardServiceImpl extends AbstractServiceImpl<GameCardMapper, GameCard>
    implements GameCardService {
    private final GameCardMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<GameCardComplexVo> pagingForMina(MinaGameCardPagingRo ro) {
        ro.setEnabled(true);
        return baseMapper.pagingForMina(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<GameCardOrdinaryVo> paging(GameCardPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertOrdinary);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GameCardOrdinaryVo> complete(GameCardCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(this::convertOrdinary).getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(GameCardSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        GameCard entity = mapping.asGameCard(ro);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, GameCardSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        GameCard entity = mapping.asGameCard(ro);
        entity.setId(id);
        entity.setLastUpdater(userId);
        entity.setLastUpdatedAt(now);
        validUpdateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameCardComplexVo getComplex(Long id) {
        return convertComplex(getById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        removeById(id);
    }


    private GameCardOrdinaryVo convertOrdinary(GameCard entity) {
        return mapping.asOrdinary(entity);
    }

    private GameCardComplexVo convertComplex(GameCard entity) {
        GameCardComplexVo result = mapping.asGameComplexVo(entity);
        String tags = entity.getTags();

        if (Strings.isNotBlank(tags)) {
            result.setTags(Lists.newArrayList(tags.split(";")));
        }

        return result;
    }
}
