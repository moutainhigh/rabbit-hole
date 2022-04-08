package in.hocg.rabbit.mina.biz.cache.impl;

import cn.hutool.core.collection.CollUtil;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.cache.RechargeCacheService;
import in.hocg.rabbit.mina.biz.constant.CacheKeys;
import in.hocg.rabbit.mina.biz.docking.recharge.RechargeDockingService;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.QueryProductRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ProductVo;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeCacheServiceImpl implements RechargeCacheService {
    private final RechargeOrderService rechargeOrderService;

    @Override
    @Cacheable(cacheNames = CacheKeys.LIST_RECHARGE_PRODUCT, key = "#userid", unless = "#result == null or #result.isEmpty()")
    public List<RechargeProductVo> listProduct(Long userid) {
        return rechargeOrderService.listProduct(userid);
    }
}
