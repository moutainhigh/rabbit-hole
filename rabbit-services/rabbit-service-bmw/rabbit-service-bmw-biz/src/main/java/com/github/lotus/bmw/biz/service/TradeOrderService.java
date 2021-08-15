package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.api.pojo.ro.CloseTradeRo;
import com.github.lotus.bmw.api.pojo.ro.CreateTradeRo;
import com.github.lotus.bmw.api.pojo.ro.GetTradeRo;
import com.github.lotus.bmw.api.pojo.ro.PayTradeRo;
import com.github.lotus.bmw.api.pojo.vo.PayTradeVo;
import com.github.lotus.bmw.api.pojo.vo.TradeSyncVo;
import com.github.lotus.bmw.biz.entity.TradeOrder;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 交易单表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface TradeOrderService extends AbstractService<TradeOrder> {

    TradeSyncVo createTrade(CreateTradeRo ro);

    TradeSyncVo closeTrade(CloseTradeRo ro);

    TradeSyncVo getTrade(GetTradeRo ro);

    TradeSyncVo getTradeById(Long tradeOrderId);

    Optional<TradeOrder> getByAccessMchIdAndOutOrderNoOrOrderNo(Long accessMchId, String outOrderNo, String orOrderNo);

    PayTradeVo goPay(PayTradeRo ro);

    void updateRefundAmtById(Long tradeOrderId, BigDecimal newRefundAmt, BigDecimal oldRefundAmt);

    boolean updatePaySuccess(Long tradeOrderId, TradeOrder update);
}
