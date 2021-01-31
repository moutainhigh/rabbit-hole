package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.biz.entity.PayRecord;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface PayRecordService extends AbstractService<PayRecord> {

    List<PayRecord> listByTradeId(Long tradeId);
}
