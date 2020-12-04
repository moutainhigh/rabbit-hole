package com.github.lotus.chaos.module.wl.service.impl;

import com.github.lotus.chaos.module.wl.entity.LogisticsLine;
import com.github.lotus.chaos.module.wl.entity.StartingPointRef;
import com.github.lotus.chaos.module.wl.entity.Warehouse;
import com.github.lotus.chaos.module.wl.mapper.StartingPointRefMapper;
import com.github.lotus.chaos.module.wl.mapstruct.StartingPointRefMapping;
import com.github.lotus.chaos.module.wl.service.StartingPointRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * <p>
 * [物流模块] 物流起点表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StartingPointRefServiceImpl extends AbstractServiceImpl<StartingPointRefMapper, StartingPointRef> implements StartingPointRefService {
    private final StartingPointRefMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void validInsertOrUpdateByLogisticsLineId(Long logisticsLineId, List<StartingPointRef> entities) {
        List<StartingPointRef> allData = this.listStartingPointRefByLogisticsLineId(logisticsLineId);

        final BiFunction<StartingPointRef, StartingPointRef, Boolean> isSame =
            (t1, t2) -> LangUtils.equals(t1.getWarehouseId(), t2.getWarehouseId());
        final List<StartingPointRef> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<StartingPointRef> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<StartingPointRef> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        this.removeByIds(deleteList.parallelStream()
            .map(StartingPointRef::getId)
            .collect(Collectors.toList()));

        // 新增
        addList.forEach(this::validInsertOrUpdate);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean hasLogisticsLineByWarehouseId(Long warehouseId) {
        return Objects.nonNull(baseMapper.hasLogisticsLineByWarehouseId(warehouseId));
    }

    @Override
    public List<LogisticsLine> listLogisticsLineByWarehouseId(Long warehouseId) {
        return baseMapper.listLogisticsLineByWarehouseId(warehouseId);
    }

    @Override
    public List<Warehouse> listWarehouseByLogisticsLineId(Long logisticsLineId) {
        return baseMapper.listWarehouseByLogisticsLineId(logisticsLineId);
    }

    private List<StartingPointRef> listStartingPointRefByLogisticsLineId(Long logisticsLineId) {
        return lambdaQuery().eq(StartingPointRef::getLogisticsLineId, logisticsLineId).list();
    }
}
