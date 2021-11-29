package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import in.hocg.rabbit.common.datadict.com.FileRelType;
import in.hocg.rabbit.mina.biz.entity.GameCard;
import in.hocg.rabbit.mina.biz.mapper.GameCardMapper;
import in.hocg.rabbit.mina.biz.mapstruct.GameCardMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardSaveRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameCardOrdinaryVo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaGameCardComplexVo;
import in.hocg.rabbit.mina.biz.service.GameCardService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
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
    private final FileServiceApi fileServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MinaGameCardComplexVo> pagingForMina(MinaGameCardPagingRo ro) {
        ro.setEnabled(true);
        return baseMapper.pagingForMina(ro, ro.ofPage()).convert(this::convertComplexForMina);
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
    public GameCardComplexVo getComplexWithMina(Long id) {
        GameCard entity = getById(id);
        ((GameCardServiceImpl) AopContext.currentProxy()).upHeatById(id);
        return convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        removeById(id);
    }

    @Async
    protected void upHeatById(Long id) {
        GameCard entity = this.getById(id);
        long randomHeat = LangUtils.getOrDefault(entity.getHeat(), 0L) + RandomUtil.randomInt(50);

        GameCard update = new GameCard();
        update.setId(id);
        update.setHeat(randomHeat);
        if (!this.updateById(update)) {
            this.upHeatById(id);
        }
    }


    private GameCardOrdinaryVo convertOrdinary(GameCard entity) {
        return mapping.asOrdinary(entity);
    }

    private GameCardComplexVo convertComplex(GameCard entity) {
        Long id = entity.getId();
        GameCardComplexVo result = mapping.asComplex(entity);
        List<FileVo> files = fileServiceApi.listByRefTypeAndRefId(FileRelType.GameCard.getCodeStr(), id);
        List<String> viewUrls = LangUtils.toList(files, FileVo::getUrl);
        if (CollUtil.isNotEmpty(files)) {
            result.setViewUrls(viewUrls);
            result.setMainViewUrl(CollUtil.getLast(viewUrls));
        }
        String tags = entity.getTags();

        if (Strings.isNotBlank(tags)) {
            result.setTags(Lists.newArrayList(tags.split(";")));
        }
        return result;
    }

    private MinaGameCardComplexVo convertComplexForMina(GameCard entity) {
        MinaGameCardComplexVo result = mapping.asGameComplexVo(entity);
        String tags = entity.getTags();

        if (Strings.isNotBlank(tags)) {
            result.setTags(Lists.newArrayList(tags.split(";")));
        }

        return result;
    }
}
