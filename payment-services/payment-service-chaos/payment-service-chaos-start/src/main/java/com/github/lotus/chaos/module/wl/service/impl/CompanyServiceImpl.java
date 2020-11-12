package com.github.lotus.chaos.module.wl.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.Company;
import com.github.lotus.chaos.module.wl.mapper.CompanyMapper;
import com.github.lotus.chaos.module.wl.mapstruct.CompanyMapping;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyPagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.CompanyComplexVo;
import com.github.lotus.chaos.module.wl.service.CompanyService;
import com.github.lotus.chaos.module.wl.service.WarehouseService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * [物流模块] 公司表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CompanyServiceImpl extends AbstractServiceImpl<CompanyMapper, Company> implements CompanyService {
    private final CompanyMapping mapping;
    private final WarehouseService warehouseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CompanyCreateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long creator = ro.getCreator();

        Company entity = mapping.asCompany(ro);
        entity.setCreator(creator);
        entity.setCreatedAt(now);
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, CompanyUpdateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long updater = ro.getUpdater();

        Company entity = mapping.asCompany(ro);
        entity.setId(id);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(updater);
        validInsertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CompanyComplexVo getCompany(Long id) {
        Company entity = getById(id);
        return convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ValidUtils.isFalse(warehouseService.hasWarehouseByCompanyId(id), "请先移除对应的仓库");
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<CompanyComplexVo> paging(CompanyPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CompanyComplexVo> complete(CompanyCompleteRo ro) {
        List<Company> records = baseMapper.complete(ro, ro.ofPage()).getRecords();
        return LangUtils.toList(records, this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Company> listCompanyByCompanyId(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(Company::getId, ids).list();
    }

    private CompanyComplexVo convertComplex(Company entity) {
        CompanyComplexVo result = mapping.asCompanyComplexVo(entity);
        Long companyId = entity.getId();
        result.setWarehouses(warehouseService.listWarehousesComplexByCompanyId(companyId));
        return result;
    }

    @Override
    public void validEntity(Company entity) {
        super.validEntity(entity);
    }
}
