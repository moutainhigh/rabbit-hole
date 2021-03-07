package com.github.lotus.pay.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.pojo.ro.RefundRecordPagingRo;
import com.github.lotus.pay.biz.pojo.vo.RefundRecordOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 退款记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface RefundRecordService extends AbstractService<RefundRecord> {

    Optional<RefundRecord> getByRefundSn(String refundSn);

    boolean updateByIdAndRefundStatus(RefundRecord updated, Long refundRecordId, String... refundStatus);

    IPage<RefundRecordOrdinaryVo> pagingByTradeId(Long tradeId, RefundRecordPagingRo ro);
}
