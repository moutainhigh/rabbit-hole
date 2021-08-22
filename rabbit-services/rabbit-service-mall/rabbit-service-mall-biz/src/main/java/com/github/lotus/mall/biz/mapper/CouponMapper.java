package com.github.lotus.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mall.biz.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mall.biz.pojo.ro.CouponPagingRo;
import com.github.lotus.mall.biz.pojo.vo.CouponComplexVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [促销模块] 优惠券表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {
    IPage<Coupon> paging(CouponPagingRo ro, Page<Object> ofPage);
}
