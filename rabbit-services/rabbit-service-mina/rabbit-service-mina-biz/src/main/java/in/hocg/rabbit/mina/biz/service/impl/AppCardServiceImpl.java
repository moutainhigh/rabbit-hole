package in.hocg.rabbit.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.AppCard;
import in.hocg.rabbit.mina.biz.mapper.AppCardMapper;
import in.hocg.rabbit.mina.biz.mapstruct.AppCardMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardSaveRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaAppCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.AppCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.AppCardOrdinaryVo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaAppCardComplexVo;
import in.hocg.rabbit.mina.biz.service.AppCardService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
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
public class AppCardServiceImpl extends AbstractServiceImpl<AppCardMapper, AppCard>
    implements AppCardService {
    private final AppCardMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MinaAppCardComplexVo> pagingForMina(MinaAppCardPagingRo ro) {
        ro.setEnabled(true);
        return baseMapper.pagingForMina(ro, ro.ofPage())
            .convert(this::convertMinaComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<AppCardOrdinaryVo> paging(AppCardPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertOrdinary);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AppCardOrdinaryVo> complete(AppCardCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(this::convertOrdinary).getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(AppCardSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        AppCard entity = mapping.asAppCard(ro);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, AppCardSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        AppCard entity = mapping.asAppCard(ro);
        entity.setId(id);
        entity.setLastUpdater(userId);
        entity.setLastUpdatedAt(now);
        validUpdateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppCardComplexVo getComplex(Long id) {
        return this.convertComplex(getById(id));
    }

    private AppCardComplexVo convertComplex(AppCard entity) {
        return mapping.asComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        removeById(id);
    }

    private AppCardOrdinaryVo convertOrdinary(AppCard entity) {
        return mapping.asOrdinary(entity);
    }

    private MinaAppCardComplexVo convertMinaComplex(AppCard entity) {
        MinaAppCardComplexVo result = mapping.asAppComplexVo(entity);
        String tags = entity.getTags();
        String pageUrl = entity.getPageUrl();
        String appid = entity.getAppid();

        if (Strings.isNotBlank(tags)) {
            result.setTags(Lists.newArrayList(tags.split(";")));
        }

        MinaAppCardComplexVo.Href href = new MinaAppCardComplexVo.Href();
        href.setMini(new MinaAppCardComplexVo.Href.Mini().setAppid(appid).setPath(pageUrl));
        result.setHref(href);

        return result;
    }
}
