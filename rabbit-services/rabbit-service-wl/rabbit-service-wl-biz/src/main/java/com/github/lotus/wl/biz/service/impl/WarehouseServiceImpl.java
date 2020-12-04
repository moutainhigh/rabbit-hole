package com.github.lotus.wl.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.wl.biz.entity.Warehouse;
import com.github.lotus.wl.biz.mapper.WarehouseMapper;
import com.github.lotus.wl.biz.mapstruct.WarehouseMapping;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCompleteRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseCreateRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseDeleteRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehousePagingRo;
import com.github.lotus.wl.biz.pojo.ro.warehouse.WarehouseUpdateRo;
import com.github.lotus.wl.biz.pojo.vo.WarehouseComplexVo;
import com.github.lotus.wl.biz.service.CompanyService;
import com.github.lotus.wl.biz.service.LogisticsLineService;
import com.github.lotus.wl.biz.service.StartingPointRefService;
import com.github.lotus.wl.biz.service.WarehouseService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    private final LogisticsLineService logisticsLineService;
    private final CompanyService companyService;
    private final StartingPointRefService startingPointRefService;

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
    public void delete(WarehouseDeleteRo ro) {
        for (Long id : ro.getId()) {
            this.delete(id);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ValidUtils.isFalse(logisticsLineService.hasLogisticsLineByWarehouseId(id), "请先移除对应的线路");
        removeById(id);
    }

    @Override
    public boolean hasWarehouseByCompanyId(Long companyId) {
        return Objects.nonNull(baseMapper.hasWarehouseByCompanyId(companyId));
    }

    private List<Warehouse> listWarehouseByCompanyId(Long companyId) {
        return lambdaQuery().eq(Warehouse::getCompanyId, companyId).list();
    }

    @Override
    public List<WarehouseComplexVo> listWarehousesComplexByCompanyId(Long companyId) {
        return LangUtils.toList(listWarehouseByCompanyId(companyId), this::convertComplex);
    }

    private List<Warehouse> listWarehouseByLogisticsLineId(Long logisticsLineId) {
        return startingPointRefService.listWarehouseByLogisticsLineId(logisticsLineId);
    }

    @Override
    public List<WarehouseComplexVo> listWarehouseComplexByLogisticsLineId(Long logisticsLineId) {
        return LangUtils.toList(listWarehouseByLogisticsLineId(logisticsLineId), this::convertComplex);
    }

    private WarehouseComplexVo convertComplex(Warehouse entity) {
        WarehouseComplexVo result = mapping.asWarehouseComplexVo(entity);
        result.setCompany(companyService.getCompany(entity.getCompanyId()));
        return result;
    }

    @Override
    public void validEntity(Warehouse entity) {
        super.validEntity(entity);

        Long companyId = entity.getCompanyId();
        if (Objects.nonNull(companyId)) {
            ValidUtils.notNull(companyService.getById(companyId), "物流公司填写错误");
        }
    }
}
