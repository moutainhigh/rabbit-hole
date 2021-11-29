package in.hocg.rabbit.bmw.biz.service;

import in.hocg.rabbit.bmw.api.pojo.ro.GetRefundRo;
import in.hocg.rabbit.bmw.api.pojo.ro.GoRefundRo;
import in.hocg.rabbit.bmw.api.pojo.vo.RefundStatusSyncVo;
import in.hocg.rabbit.bmw.biz.entity.RefundRecord;
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
