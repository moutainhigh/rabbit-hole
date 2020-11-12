package com.github.lotus.chaos.module.wl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.mapper.LogisticsLineMapper;
import com.github.lotus.chaos.module.wl.mapstruct.LogisticsLineMapping;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLinePagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.LogisticsLineComplexVo;
import com.github.lotus.chaos.module.wl.service.LogisticsLineService;
import com.github.lotus.chaos.module.wl.service.WarehouseLogisticsLineRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * [物流模块] 物流线路表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LogisticsLineServiceImpl extends AbstractServiceImpl<LogisticsLineMapper, LogisticsLine> implements LogisticsLineService {
    private final LogisticsLineMapping mapping;
    private final WarehouseLogisticsLineRefService warehouseLogisticsLineRefService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, LogisticsLineUpdateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long updater = ro.getUpdater();

        LogisticsLine entity = mapping.asLogisticsLine(ro);
        entity.setId(id);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(updater);
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(LogisticsLineCreateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long creator = ro.getCreator();

        LogisticsLine entity = mapping.asWarehouse(ro);
        entity.setCreator(creator);
        entity.setCreatedAt(now);
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LogisticsLineComplexVo getLogisticsLine(Long id) {
        LogisticsLine entity = getById(id);
        return convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<LogisticsLineComplexVo> paging(LogisticsLinePagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<LogisticsLineComplexVo> complete(LogisticsLineCompleteRo ro) {
        List<LogisticsLine> records = baseMapper.complete(ro, ro.ofPage()).getRecords();
        return LangUtils.toList(records, this::convertComplex);
    }

    @Override
    public boolean hasByWarehouseId(Long warehouseId) {
        return warehouseLogisticsLineRefService.hasByWarehouseId(warehouseId);
    }

    private LogisticsLineComplexVo convertComplex(LogisticsLine entity) {
        return mapping.asLogisticsLineComplexVo(entity);
    }
}
