package com.github.lotus.chaos.module.wl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.entity.StartingPointRef;
import com.github.lotus.chaos.module.wl.mapper.LogisticsLineMapper;
import com.github.lotus.chaos.module.wl.mapstruct.LogisticsLineMapping;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineBatchCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLinePagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.LogisticsLineComplexVo;
import com.github.lotus.chaos.module.wl.service.LogisticsLineService;
import com.github.lotus.chaos.module.wl.service.StartingPointRefService;
import com.github.lotus.chaos.module.wl.service.WarehouseService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final WarehouseService warehouseService;
    private final StartingPointRefService startingPointRefService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long logisticsLineId, LogisticsLineUpdateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long updater = ro.getUpdater();
        List<Long> warehouseId = ro.getWarehouseId();

        LogisticsLine entity = mapping.asLogisticsLine(ro);
        entity.setId(logisticsLineId);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(updater);
        validInsertOrUpdate(entity);

        if (Objects.nonNull(warehouseId)) {
            startingPointRefService.validInsertOrUpdateByLogisticsLineId(logisticsLineId, warehouseId.parallelStream()
                .map(id -> new StartingPointRef().setWarehouseId(id).setLogisticsLineId(logisticsLineId))
                .collect(Collectors.toList()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(LogisticsLineCreateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long creator = ro.getCreator();
        List<Long> warehouseId = ro.getWarehouseId();

        LogisticsLine entity = mapping.asLogisticsLine(ro);
        entity.setCreator(creator);
        entity.setCreatedAt(now);
        validInsertOrUpdate(entity);
        Long logisticsLineId = entity.getId();

        if (Objects.nonNull(warehouseId)) {
            startingPointRefService.validInsertOrUpdateByLogisticsLineId(logisticsLineId, warehouseId.parallelStream()
                .map(id -> new StartingPointRef().setWarehouseId(id).setLogisticsLineId(logisticsLineId))
                .collect(Collectors.toList()));
        }
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
    @Transactional(rollbackFor = Exception.class)
    public boolean hasLogisticsLineByWarehouseId(Long warehouseId) {
        return startingPointRefService.hasLogisticsLineByWarehouseId(warehouseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<LogisticsLineComplexVo> listLogisticsLineComplexByWarehouseId(Long warehouseId) {
        return LangUtils.toList(listLogisticsLineByWarehouseId(warehouseId), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCreate(LogisticsLineBatchCreateRo ro) {
        Long creator = ro.getCreator();
        List<Long> warehouseId = ro.getWarehouseId();
        String provinceAdcode = ro.getProvinceAdcode();
        String cityAdcode = ro.getCityAdcode();

        for (LogisticsLineBatchCreateRo.LogisticsLineCreateRo logisticsLine : ro.getLogisticsLines()) {
            LogisticsLineCreateRo logisticsLineCreateRo = mapping.aslogisticsLineCreateRo(logisticsLine,
                provinceAdcode, cityAdcode, warehouseId, creator);
            this.create(logisticsLineCreateRo);
        }
    }

    private List<LogisticsLine> listLogisticsLineByWarehouseId(Long warehouseId) {
        return startingPointRefService.listLogisticsLineByWarehouseId(warehouseId);
    }

    private LogisticsLineComplexVo convertComplex(LogisticsLine entity) {
        LogisticsLineComplexVo result = mapping.asLogisticsLineComplexVo(entity);
        result.setWarehouses(warehouseService.listWarehouseComplexByLogisticsLineId(entity.getId()));
        return result;
    }

    @Override
    public void validEntity(LogisticsLine entity) {
        super.validEntity(entity);
    }
}
