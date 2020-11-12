package com.github.lotus.chaos.module.wl.mapstruct;

import com.github.lotus.chaos.module.wl.entity.Company;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyCreateRo;
import com.github.lotus.chaos.module.wl.pojo.ro.company.CompanyUpdateRo;
import com.github.lotus.chaos.module.wl.pojo.vo.CompanyComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface CompanyMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Company asCompany(CompanyCreateRo ro);

    Company asCompany(CompanyUpdateRo ro);

    CompanyComplexVo asCompanyComplexVo(Company entity);
}
