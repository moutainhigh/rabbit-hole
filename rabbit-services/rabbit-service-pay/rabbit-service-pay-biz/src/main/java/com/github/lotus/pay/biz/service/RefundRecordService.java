package com.github.lotus.pay.biz.service;


import com.github.lotus.pay.biz.entity.RefundRecord;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 退款记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
public interface RefundRecordService extends AbstractService<RefundRecord> {

    Optional<RefundRecord> selectOneByRefundSn(String refundSn);

    boolean updateOneByIdAndTradeStatus(RefundRecord updated, Long id, Integer refundStatus);
}
