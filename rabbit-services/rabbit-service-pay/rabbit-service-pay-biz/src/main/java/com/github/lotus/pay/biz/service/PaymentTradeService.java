package com.github.lotus.pay.biz.service;


import com.github.lotus.pay.biz.entity.PaymentTrade;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 交易流水表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
public interface PaymentTradeService extends AbstractService<PaymentTrade> {

    Optional<PaymentTrade> selectOneByTradeSn(String tradeSn);

    boolean updateOneByIdAndTradeStatus(PaymentTrade update, Long tradeId, Integer... tradeStatus);

}
