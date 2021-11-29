package in.hocg.rabbit.bmw.biz.service;

import in.hocg.rabbit.bmw.biz.entity.SyncAccessMchTask;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [支付模块] 通知接入商户任务表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface SyncAccessMchTaskService extends AbstractService<SyncAccessMchTask> {

    void createPayed(Long tradeOrderId);

    void createRefund(Long refundRecordId);

    List<SyncAccessMchTask> listByUnHandle();

    boolean updateStatusByIdAndStatus(Long id, String status, String targetStatus);

    void updateDoneById(Long entityId, String requestBody, String returnBody, boolean isSuccess);

    void reCreate(Long prevTaskId);
}
