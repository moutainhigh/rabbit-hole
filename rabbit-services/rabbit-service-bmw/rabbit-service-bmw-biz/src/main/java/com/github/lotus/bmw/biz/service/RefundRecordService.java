package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.api.pojo.ro.GetRefundRo;
import com.github.lotus.bmw.api.pojo.ro.GoRefundRo;
import com.github.lotus.bmw.api.pojo.vo.RefundStatusSyncVo;
import com.github.lotus.bmw.api.pojo.vo.TradeStatusSyncVo;
import com.github.lotus.bmw.biz.entity.RefundRecord;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import lombok.NonNull;

import java.util.Optional;

/**
 * <p>
 * [支付模块] 退款记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface RefundRecordService extends AbstractService<RefundRecord> {

    RefundStatusSyncVo getRefund(GetRefundRo ro);

    RefundStatusSyncVo goRefund(GoRefundRo ro);

    RefundStatusSyncVo getRefundById(Long refundRecordId);

    boolean updateByIdAndStatus(@NonNull RefundRecord update, @NonNull Long refundRecordId, @NonNull String... refundStatus);

    RefundStatusSyncVo convertRefundSyncVo(RefundRecord entity);

    Optional<RefundRecord> getByAccessMchIdAndOutRefundNoOrRefundNo(Long accessMchId, String outOrderNo, String orOrderNo);

}
