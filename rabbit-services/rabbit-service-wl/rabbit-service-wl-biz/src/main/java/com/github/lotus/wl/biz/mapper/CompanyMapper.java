package com.github.lotus.wl.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.wl.biz.entity.Company;
import com.github.lotus.wl.biz.pojo.ro.company.CompanyCompleteRo;
import com.github.lotus.wl.biz.pojo.ro.company.CompanyPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [物流模块] 公司表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
    IPage<Company> paging(@Param("ro") CompanyPagingRo ro, @Param("ofPage") Page<Company> ofPage);

    IPage<Company> complete(@Param("ro") CompanyCompleteRo ro, @Param("ofPage") Page ofPage);
}
