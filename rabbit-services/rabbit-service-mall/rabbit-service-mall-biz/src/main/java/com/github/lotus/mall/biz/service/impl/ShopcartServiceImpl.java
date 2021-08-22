package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.Shopcart;
import com.github.lotus.mall.biz.mapper.ShopcartMapper;
import com.github.lotus.mall.biz.service.ShopcartService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [订单模块] 购物车表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShopcartServiceImpl extends AbstractServiceImpl<ShopcartMapper, Shopcart> implements ShopcartService {

}
