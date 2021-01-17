package com.github.lotus.pay.biz.service;


import com.github.lotus.pay.biz.entity.PaymentRecord;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
public interface PaymentRecordService extends AbstractService<PaymentRecord> {

    List<PaymentRecord> selectListByTradeId(Long tradeId);
}
