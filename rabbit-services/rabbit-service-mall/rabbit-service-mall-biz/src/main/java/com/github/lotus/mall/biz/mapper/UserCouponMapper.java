package com.github.lotus.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mall.biz.entity.UserCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mall.biz.pojo.ro.UserCouponPagingRo;
import com.github.lotus.mall.biz.pojo.vo.UserCouponComplexVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [促销模块] 优惠券账号拥有人表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface UserCouponMapper extends BaseMapper<UserCoupon> {

    IPage<UserCoupon> paging(@Param("ro") UserCouponPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
