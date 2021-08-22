package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.CouponProductRef;
import com.github.lotus.mall.biz.mapper.CouponProductRefMapper;
import com.github.lotus.mall.biz.service.CouponProductRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [促销模块] 优惠券可用商品表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CouponProductRefServiceImpl extends AbstractServiceImpl<CouponProductRefMapper, CouponProductRef> implements CouponProductRefService {

}
