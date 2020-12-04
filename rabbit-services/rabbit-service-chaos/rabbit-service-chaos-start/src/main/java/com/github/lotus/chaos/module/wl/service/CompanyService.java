package com.github.lotus.chaos.module.wl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.chaos.module.wl.entity.Company;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyCompleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyDeleteRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyPagingRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.CompanyComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [物流模块] 公司表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
public interface CompanyService extends AbstractService<Company> {

    void create(CompanyCreateRo ro);

    void update(Long id, CompanyUpdateRo ro);

    CompanyComplexVo getCompany(Long id);

    void delete(Long id);

    IPage<CompanyComplexVo> paging(CompanyPagingRo ro);

    List<CompanyComplexVo> complete(CompanyCompleteRo ro);

    List<Company> listCompanyByCompanyId(List<Long> values);

    void delete(CompanyDeleteRo ro);
}
