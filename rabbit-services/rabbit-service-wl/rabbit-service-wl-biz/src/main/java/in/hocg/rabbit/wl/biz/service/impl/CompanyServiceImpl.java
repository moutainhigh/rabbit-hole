package in.hocg.rabbit.wl.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.wl.biz.entity.Company;
import in.hocg.rabbit.wl.biz.mapper.CompanyMapper;
import in.hocg.rabbit.wl.biz.mapstruct.CompanyMapping;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyDeleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyPagingRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyUpdateRo;
import in.hocg.rabbit.wl.biz.pojo.vo.CompanyComplexVo;
import in.hocg.rabbit.wl.biz.service.CompanyService;
import in.hocg.rabbit.wl.biz.service.WarehouseService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
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
public class CompanyServiceImpl extends AbstractServiceImpl<CompanyMapper, Company>
    implements CompanyService {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CompanyDeleteRo ro) {
        for (Long id : ro.getId()) {
            this.delete(id);
        }
    }

    private CompanyComplexVo convertComplex(Company entity) {
        CompanyComplexVo result = mapping.asCompanyComplexVo(entity);
        Long companyId = entity.getId();
        return result;
    }

    @Override
    public void validEntity(Company entity) {
        super.validEntity(entity);
    }
}
