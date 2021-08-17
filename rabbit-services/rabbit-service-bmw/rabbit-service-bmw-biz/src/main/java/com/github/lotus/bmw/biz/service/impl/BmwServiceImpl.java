package com.github.lotus.bmw.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.github.lotus.bmw.api.pojo.ro.GetCashierRo;
import com.github.lotus.bmw.biz.cache.BmwCacheService;
import com.github.lotus.bmw.biz.cache.impl.BmwCacheServiceImpl;
import com.github.lotus.bmw.biz.entity.AccessMch;
import com.github.lotus.bmw.biz.entity.PayScene;
import com.github.lotus.bmw.biz.entity.TradeOrder;
import com.github.lotus.bmw.biz.mapstruct.BmwMapping;
import com.github.lotus.bmw.biz.pojo.dto.CashierInfoDto;
import com.github.lotus.bmw.biz.pojo.ro.CloseTradeRo;
import com.github.lotus.bmw.biz.pojo.ro.GoPayRo;
import com.github.lotus.bmw.biz.pojo.vo.CashierInfoVo;
import com.github.lotus.bmw.biz.pojo.vo.GoPayVo;
import com.github.lotus.bmw.biz.service.BmwService;
import com.github.lotus.bmw.biz.service.PaySceneService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import com.github.lotus.bmw.biz.support.PageUrlHelper;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BmwServiceImpl implements BmwService {
    private final BmwCacheService bmwCacheService;
    private final TradeOrderService tradeOrderService;
    private final PaySceneService paySceneService;
    private final BmwMapping mapping;

    @Override
    public CashierInfoVo getCashierInfo(String u) {
        CashierInfoDto cashierInfo = bmwCacheService.getCashierInfo(u)
            .orElseThrow(() -> ServiceException.wrap("交易错误"));
        Long paySceneId = cashierInfo.getPaySceneId();

        Long tradeOrderId = cashierInfo.getTradeOrderId();
        TradeOrder tradeOrder = tradeOrderService.getById(tradeOrderId);
        CashierInfoVo result = mapping.asCashierInfoVo(tradeOrder);
        result.setPaySceneId(paySceneId);
        result.setPayTypes(paySceneService.listByPaySceneId(paySceneId));
        return result;
    }

    @Override
    public String getCashierUrl(GetCashierRo ro) {
        AccessMch accessMch = bmwCacheService.getAccessMchByEncoding(ro.getAccessCode());
        Long accessMchId = accessMch.getId();
        TradeOrder tradeOrder = tradeOrderService.getByAccessMchIdAndOutTradeNoOrTradeNo(accessMchId, ro.getOutTradeNo(), ro.getTradeNo())
            .orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
        PayScene payScene = paySceneService.getByAccessMchIdAndEncoding(accessMchId, ro.getSceneCode())
            .orElseThrow(() -> ServiceException.wrap("未找到支付场景"));

        CashierInfoDto cashierInfo = new CashierInfoDto()
            .setTradeOrderId(tradeOrder.getId())
            .setPaySceneId(payScene.getId())
            .setAccessMchId(accessMchId);
        String u = bmwCacheService.setCashierInfo(cashierInfo);
        return PageUrlHelper.getCashierUrl(u);
    }

    @Override
    public GoPayVo goPay(GoPayRo ro) {
        return tradeOrderService.goPay(ro);
    }

    @Override
    public void closeTrade(CloseTradeRo ro) {
        tradeOrderService.closeTrade(ro.getTradeOrderId());
    }
}
