package com.github.lotus.mall.biz.service.impl;

import com.github.lotus.mall.biz.entity.Sku;
import com.github.lotus.mall.biz.mapper.SkuMapper;
import com.github.lotus.mall.biz.service.SkuService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [商品模块] 商品SKU表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SkuServiceImpl extends AbstractServiceImpl<SkuMapper, Sku> implements SkuService {

}
