package com.github.lotus.mall.biz.mapper;

import com.github.lotus.mall.biz.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mall.biz.pojo.ro.ProductCategoryTreeRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [商品模块] 商品品类表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<ProductCategory> listTree(@Param("ro") ProductCategoryTreeRo ro);

    /**
     * 商品品类是否正在被商品使用
     *
     * @param regexTreePath
     * @return
     */
    Integer countUsedProduct(@Param("regexTreePath") String regexTreePath);

    /**
     * 商品品类是否正在被优惠券使用
     *
     * @param regexTreePath
     * @return
     */
    Integer countUsedCoupon(@Param("regexTreePath") String regexTreePath);
}
