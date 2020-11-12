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
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Company asCompany(CompanyCreateRo ro);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Company asCompany(CompanyUpdateRo ro);

    CompanyComplexVo asCompanyComplexVo(Company entity);
}
