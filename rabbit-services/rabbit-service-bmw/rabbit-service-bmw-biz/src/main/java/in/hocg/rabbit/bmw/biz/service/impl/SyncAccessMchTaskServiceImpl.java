package in.hocg.rabbit.bmw.biz.service.impl;

import in.hocg.rabbit.bmw.biz.entity.RefundRecord;
import in.hocg.rabbit.bmw.biz.entity.SyncAccessMchTask;
import in.hocg.rabbit.bmw.biz.entity.TradeOrder;
import in.hocg.rabbit.bmw.biz.mapper.SyncAccessMchTaskMapper;
import in.hocg.rabbit.bmw.biz.schedule.SyncAccessMchTaskSchedule;
import in.hocg.rabbit.bmw.biz.service.RefundRecordService;
import in.hocg.rabbit.bmw.biz.service.SyncAccessMchTaskService;
import in.hocg.rabbit.bmw.biz.service.TradeOrderService;
import in.hocg.rabbit.bmw.biz.support.TaskHelper;
import in.hocg.rabbit.common.datadict.common.HandleStatus;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.common.datadict.bmw.SyncAccessMchTaskType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * [支付模块] 通知接入商户任务表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SyncAccessMchTaskServiceImpl extends AbstractServiceImpl<SyncAccessMchTaskMapper, SyncAccessMchTask>
    implements SyncAccessMchTaskService {
    private final SyncAccessMchTaskSchedule syncAccessMchTaskSchedule;
    private final TradeOrderService tradeOrderService;
    private final RefundRecordService refundRecordService;

    @Async
    @Override
    public void createPayed(Long tradeOrderId) {
        TradeOrder tradeOrder = tradeOrderService.getById(tradeOrderId);
        LocalDateTime now = LocalDateTime.now();

        SyncAccessMchTask entity = new SyncAccessMchTask();
        entity.setTaskType(SyncAccessMchTaskType.TradeResult.getCodeStr());
        entity.setNotifyCount(1);
        entity.setPlanNotifyAt(now);
        entity.setRefId(tradeOrderId);
        entity.setRefType(RefType.TradeOrder.getCodeStr());
        entity.setNotifyUrl(tradeOrder.getNotifyUrl());
        entity.setCreatedAt(now);
        entity.setStatus(HandleStatus.Init.getCodeStr());
        this.validInsert(entity);
        syncAccessMchTaskSchedule.asyncNotifyHandleError(entity);
    }

    @Async
    @Override
    public void createRefund(Long refundRecordId) {
        RefundRecord refundRecord = refundRecordService.getById(refundRecordId);
        LocalDateTime now = LocalDateTime.now();

        SyncAccessMchTask entity = new SyncAccessMchTask();
        entity.setTaskType(SyncAccessMchTaskType.TradeResult.getCodeStr());
        entity.setNotifyCount(1);
        entity.setPlanNotifyAt(now);
        entity.setRefId(refundRecordId);
        entity.setRefType(RefType.RefundRecord.getCodeStr());
        entity.setNotifyUrl(refundRecord.getNotifyUrl());
        entity.setCreatedAt(now);
        entity.setStatus(HandleStatus.Init.getCodeStr());
        this.validInsert(entity);
        syncAccessMchTaskSchedule.asyncNotifyHandleError(entity);
    }

    @Override
    public List<SyncAccessMchTask> listByUnHandle() {
        return this.lambdaQuery().eq(SyncAccessMchTask::getStatus, HandleStatus.Init.getCodeStr())
            .le(SyncAccessMchTask::getPlanNotifyAt, new Date()).list();
    }

    @Override
    public boolean updateStatusByIdAndStatus(Long id, String status, String targetStatus) {
        SyncAccessMchTask update = new SyncAccessMchTask();
        update.setStatus(targetStatus);
        return this.lambdaUpdate().eq(SyncAccessMchTask::getId, id).eq(SyncAccessMchTask::getStatus, status)
            .update(update);
    }

    @Override
    public void updateDoneById(Long entityId, String requestBody, String returnBody, boolean isSuccess) {
        SyncAccessMchTask update = new SyncAccessMchTask();
        update.setId(entityId);
        update.setNotifyBody(requestBody);
        update.setReturnBody(returnBody);

        update.setFinishedAt(LocalDateTime.now());
        update.setStatus(isSuccess ? HandleStatus.Success.getCodeStr() : HandleStatus.Fail.getCodeStr());
        this.validUpdateById(update);
    }

    @Override
    public void reCreate(Long prevTaskId) {
        SyncAccessMchTask preTask = getById(prevTaskId);

        SyncAccessMchTask newEntity = new SyncAccessMchTask();
        newEntity.setTaskType(preTask.getTaskType());
        int realCount = preTask.getNotifyCount() + 1;
        newEntity.setNotifyCount(realCount);
        newEntity.setStatus(HandleStatus.Init.getCodeStr());
        newEntity.setRefId(preTask.getRefId());
        newEntity.setRefType(preTask.getRefType());

        Long delaySecond = TaskHelper.getDelaySecond(realCount);
        LocalDateTime planNotifyDate = LocalDateTime.now().plus(delaySecond, ChronoUnit.SECONDS);
        newEntity.setPlanNotifyAt(planNotifyDate);
        this.validInsert(newEntity);
    }
}
