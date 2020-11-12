package com.github.lotus.chaos.module.wl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.Warehouse;
import com.github.lotus.chaos.module.wl.mapper.WarehouseMapper;
import com.github.lotus.chaos.module.wl.mapstruct.WarehouseMapping;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehousePagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.WarehouseComplexVo;
import com.github.lotus.chaos.module.wl.service.WarehouseService;
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
 * [物流模块] 仓库表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WarehouseServiceImpl extends AbstractServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {
    private final WarehouseMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(WarehouseCreateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long creator = ro.getCreator();

        Warehouse entity = mapping.asWarehouse(ro);
        entity.setCreator(creator);
        entity.setCreatedAt(now);
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, WarehouseUpdateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long updater = ro.getUpdater();

        Warehouse entity = mapping.asWarehouse(ro);
        entity.setId(id);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(updater);
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<WarehouseComplexVo> complete(WarehouseCompleteRo ro) {
        List<Warehouse> records = baseMapper.complete(ro, ro.ofPage()).getRecords();
        return LangUtils.toList(records, this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<WarehouseComplexVo> paging(WarehousePagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WarehouseComplexVo getWarehouse(Long id) {
        Warehouse entity = getById(id);
        return convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        removeById(id);
    }

    private WarehouseComplexVo convertComplex(Warehouse entity) {
        return mapping.asWarehouseComplexVo(entity);
    }
}
