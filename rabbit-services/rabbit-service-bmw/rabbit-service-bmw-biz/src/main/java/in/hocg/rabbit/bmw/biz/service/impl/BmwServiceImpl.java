package in.hocg.rabbit.bmw.biz.service.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.bmw.api.pojo.ro.GetCashierRo;
import in.hocg.rabbit.bmw.biz.cache.BmwCacheService;
import in.hocg.rabbit.bmw.biz.entity.AccessMch;
import in.hocg.rabbit.bmw.biz.entity.PayScene;
import in.hocg.rabbit.bmw.biz.entity.TradeOrder;
import in.hocg.rabbit.bmw.biz.mapstruct.BmwMapping;
import in.hocg.rabbit.bmw.biz.pojo.dto.CashierInfoDto;
import in.hocg.rabbit.bmw.biz.pojo.ro.CloseTradeRo;
import in.hocg.rabbit.bmw.biz.pojo.ro.GoPayRo;
import in.hocg.rabbit.bmw.biz.pojo.vo.CashierInfoVo;
import in.hocg.rabbit.bmw.biz.pojo.vo.GoPayVo;
import in.hocg.rabbit.bmw.biz.service.BmwService;
import in.hocg.rabbit.bmw.biz.service.PaySceneService;
import in.hocg.rabbit.bmw.biz.service.TradeOrderService;
import in.hocg.rabbit.bmw.biz.support.PageUrlHelper;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
        Optional<CashierInfoDto> cashierInfoOpt = bmwCacheService.getCashierInfo(u);
        if (!cashierInfoOpt.isPresent()) {
            return null;
        }
        CashierInfoDto cashierInfo = cashierInfoOpt.get();
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
        Assert.notNull(accessMch, "未找到接入商户");
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

    @Override
    public String getCashierPage(String key) {
        return bmwCacheService.getFormPage(key).orElseThrow(() -> ServiceException.wrap("连接已过期"));
    }
}
