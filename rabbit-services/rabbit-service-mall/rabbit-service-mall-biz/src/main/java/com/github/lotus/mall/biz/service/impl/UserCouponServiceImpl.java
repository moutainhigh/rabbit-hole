package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.UserCoupon;
import com.github.lotus.mall.biz.mapper.UserCouponMapper;
import com.github.lotus.mall.biz.pojo.ro.UserCouponPagingRo;
import com.github.lotus.mall.biz.pojo.vo.UserCouponComplexVo;
import com.github.lotus.mall.biz.service.UserCouponService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [促销模块] 优惠券账号拥有人表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserCouponServiceImpl extends AbstractServiceImpl<UserCouponMapper, UserCoupon> implements UserCouponService {

    @Override
    public IPage<UserCouponComplexVo> paging(UserCouponPagingRo ro) {
        return null;
    }

    @Override
    public void revokeById(Long id) {

    }
}
