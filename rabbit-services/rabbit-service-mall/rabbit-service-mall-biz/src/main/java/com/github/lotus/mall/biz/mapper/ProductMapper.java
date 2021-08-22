package com.github.lotus.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mall.biz.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mall.biz.pojo.ro.ProductCompleteRo;
import com.github.lotus.mall.biz.pojo.ro.ProductPagingRo;
import com.github.lotus.mall.biz.pojo.vo.ProductComplexVo;
import com.google.common.base.Converter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> complete(@Param("ro") ProductCompleteRo ro, @Param("ofPage") Page ofPage);

    IPage<Product> paging(@Param("ro") ProductPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
