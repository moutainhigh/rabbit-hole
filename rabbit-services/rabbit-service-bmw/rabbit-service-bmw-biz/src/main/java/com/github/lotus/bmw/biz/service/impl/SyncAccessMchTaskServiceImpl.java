package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.RefundRecord;
import com.github.lotus.bmw.biz.entity.SyncAccessMchTask;
import com.github.lotus.bmw.biz.entity.TradeOrder;
import com.github.lotus.bmw.biz.mapper.SyncAccessMchTaskMapper;
import com.github.lotus.bmw.biz.schedule.SyncAccessMchTaskSchedule;
import com.github.lotus.bmw.biz.service.RefundRecordService;
import com.github.lotus.bmw.biz.service.SyncAccessMchTaskService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import com.github.lotus.bmw.biz.support.TaskHelper;
import com.github.lotus.common.datadict.RefType;
import com.github.lotus.common.datadict.bmw.SyncAccessMchTaskStatus;
import com.github.lotus.common.datadict.bmw.SyncAccessMchTaskType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
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
        entity.setStatus(SyncAccessMchTaskStatus.Init.getCodeStr());
        this.validInsert(entity);
        syncAccessMchTaskSchedule.asyncNotifyHandleError(entity);
    }

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
        entity.setStatus(SyncAccessMchTaskStatus.Init.getCodeStr());
        this.validInsert(entity);
        syncAccessMchTaskSchedule.asyncNotifyHandleError(entity);
    }

    @Override
    public List<SyncAccessMchTask> listByUnHandle() {
        return this.lambdaQuery().eq(SyncAccessMchTask::getStatus, SyncAccessMchTaskStatus.Init.getCodeStr())
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
        update.setStatus(isSuccess ? SyncAccessMchTaskStatus.Success.getCodeStr() : SyncAccessMchTaskStatus.Fail.getCodeStr());
        this.validUpdateById(update);
    }

    @Override
    public void reCreate(Long prevTaskId) {
        SyncAccessMchTask preTask = getById(prevTaskId);

        SyncAccessMchTask newEntity = new SyncAccessMchTask();
        newEntity.setTaskType(preTask.getTaskType());
        int realCount = preTask.getNotifyCount() + 1;
        newEntity.setNotifyCount(realCount);
        newEntity.setStatus(SyncAccessMchTaskStatus.Init.getCodeStr());
        newEntity.setRefId(preTask.getRefId());
        newEntity.setRefType(preTask.getRefType());

        Long delaySecond = TaskHelper.getDelaySecond(realCount);
        LocalDateTime planNotifyDate = LocalDateTime.now().plus(delaySecond, ChronoUnit.SECONDS);
        newEntity.setPlanNotifyAt(planNotifyDate);
        this.validInsert(newEntity);
    }
}
