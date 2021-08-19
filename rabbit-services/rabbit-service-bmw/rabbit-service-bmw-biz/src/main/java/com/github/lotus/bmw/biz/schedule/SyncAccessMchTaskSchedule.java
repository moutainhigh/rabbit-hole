package com.github.lotus.bmw.biz.schedule;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import com.github.lotus.bmw.api.pojo.vo.RefundStatusSyncVo;
import com.github.lotus.bmw.api.pojo.vo.TradeStatusSyncVo;
import com.github.lotus.bmw.biz.entity.SyncAccessMchTask;
import com.github.lotus.bmw.biz.pojo.dto.NotifySyncStatusVo;
import com.github.lotus.bmw.biz.service.RefundRecordService;
import com.github.lotus.bmw.biz.service.SyncAccessMchTaskService;
import com.github.lotus.bmw.biz.service.TradeOrderService;
import com.github.lotus.bmw.biz.support.TaskHelper;
import com.github.lotus.common.datadict.bmw.SyncAccessMchTaskStatus;
import com.github.lotus.common.datadict.bmw.SyncAccessMchTaskType;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SyncAccessMchTaskSchedule extends JavaProcessor {
    private final SyncAccessMchTaskService syncAccessMchTaskService;
    private final TradeOrderService tradeOrderService;
    private final RefundRecordService refundRecordService;
    private final SyncAccessMchTaskSchedule self;

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        syncAccessMchTaskService.listByUnHandle().forEach(self::asyncNotifyHandleError);
        return new ProcessResult(true);
    }

    @Async
    public void asyncNotifyHandleError(SyncAccessMchTask entity) {
        try {
            this.asyncNotify(entity);
        } catch (Exception e) {
            log.warn("通知接入商户发生错误, 同步任务编号[ID={}], 错误信息: {}", entity.getId(), e);
            String returnBody = StrUtil.format("通知接入商户发生错误: {}", e.getMessage());
            syncAccessMchTaskService.updateDoneById(entity.getId(), null, returnBody, false);
        }
    }

    private void asyncNotify(SyncAccessMchTask entity) {
        // 1. 开始通知
        Long entityId = entity.getId();
        Integer realCount = LangUtils.getOrDefault(entity.getNotifyCount(), 1);
        boolean isOk = syncAccessMchTaskService.updateStatusByIdAndStatus(entityId, entity.getStatus(), SyncAccessMchTaskStatus.Processing.getCodeStr());
        if (!isOk) {
            log.warn("任务[id={}]状态不符合预期，系统终止继续执行", entityId);
            return;
        }

        // 2. 通知准备
        String notifyUrl = entity.getNotifyUrl();
        String signStr = null;
        NotifySyncStatusVo notifyBody = new NotifySyncStatusVo();
        notifyBody.setNotifyAt(LocalDateTime.now());
        notifyBody.setSyncNotifyType(entity.getTaskType());

        // 交易结果
        if (SyncAccessMchTaskType.TradeResult.eq(entity.getTaskType())) {
            TradeStatusSyncVo syncResult = tradeOrderService.getTradeById(entity.getRefId());
            notifyBody.setTradeStatusSync(syncResult);
            signStr = MD5.create().digestHex(JSON.toJSONString(syncResult));
        }
        // 退款结果
        else if (SyncAccessMchTaskType.RefundResult.eq(entity.getTaskType())) {
            RefundStatusSyncVo syncResult = refundRecordService.getRefundById(entity.getRefId());
            notifyBody.setRefundStatusSync(syncResult);
            signStr = MD5.create().digestHex(JSON.toJSONString(syncResult));
        }

        if (Strings.isBlank(notifyUrl)) {
            log.warn("任务[id={}]没有通知地址，系统终止继续执行", entityId);
            syncAccessMchTaskService.updateDoneById(entityId, null, "not notify url", false);
            return;
        }

        // 3. 进行通知
        notifyBody.setSign(signStr);
        String requestBody = JSON.toJSONString(notifyBody);
        String returnBody = HttpUtil.post(notifyUrl, requestBody, 2 * 1000);

        // 4. 通知完成
        boolean isSuccess = "notify_success".equalsIgnoreCase(StrUtil.nullToEmpty(returnBody).trim());
        syncAccessMchTaskService.updateDoneById(entityId, requestBody, returnBody, isSuccess);

        // 5. 如果失败，创建下一个通知任务
        if (!isSuccess && realCount < TaskHelper.MAX_REPLAY_COUNT) {
            syncAccessMchTaskService.reCreate(entityId);
        }
    }
}
