package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.pojo.ro.AccessAppCompleteRo;
import com.github.lotus.pay.biz.pojo.ro.AccessAppPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [支付网关] 接入应用表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface AccessAppMapper extends BaseMapper<AccessApp> {

    IPage<AccessApp> paging(@Param("ro") AccessAppPagingRo ro, @Param("page") Page page);

    IPage<AccessApp> complete(@Param("ro") AccessAppCompleteRo ro, @Param("page") Page page);
}
