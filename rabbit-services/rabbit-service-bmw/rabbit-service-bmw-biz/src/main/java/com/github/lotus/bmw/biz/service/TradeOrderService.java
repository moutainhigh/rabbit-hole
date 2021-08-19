package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.api.pojo.ro.CloseTradeRo;
import com.github.lotus.bmw.api.pojo.ro.CreateTradeRo;
import com.github.lotus.bmw.api.pojo.ro.GetTradeRo;
import com.github.lotus.bmw.biz.pojo.ro.GoPayRo;
import com.github.lotus.bmw.biz.pojo.vo.GoPayVo;
import com.github.lotus.bmw.api.pojo.vo.TradeStatusSyncVo;
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

    TradeStatusSyncVo createTrade(CreateTradeRo ro);

    TradeStatusSyncVo closeTrade(CloseTradeRo ro);

    TradeStatusSyncVo getTrade(GetTradeRo ro);

    TradeStatusSyncVo getTradeById(Long tradeOrderId);

    void closeTrade(Long tradeOrderId);

    Optional<TradeOrder> getByAccessMchIdAndOutTradeNoOrTradeNo(Long accessMchId, String outOrderNo, String orOrderNo);

    GoPayVo goPay(GoPayRo ro);

    void updateRefundAmtById(Long tradeOrderId, BigDecimal newRefundAmt, BigDecimal oldRefundAmt);

    boolean updatePaySuccess(Long tradeOrderId, TradeOrder update);

    Optional<TradeOrder> getByOrderNo(String orderNo);

    void paySuccess(Long payRecordId);
}
