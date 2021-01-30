package com.github.lotus.pay.biz.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.lotus.common.datadict.bmw.NotifyResult;
import com.github.lotus.common.datadict.bmw.NotifyType;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.entity.NotifyAccessApp;
import com.github.lotus.pay.biz.entity.NotifyAccessAppLog;
import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.biz.mapper.AccessAppMapper;
import com.github.lotus.pay.biz.mapstruct.AccessAppMapping;
import com.github.lotus.pay.biz.pojo.ro.AccessAppInsertRo;
import com.github.lotus.pay.biz.pojo.vo.NotifyAppAsyncVo;
import com.github.lotus.pay.biz.service.AccessAppService;
import com.github.lotus.pay.biz.service.AllPaymentService;
import com.github.lotus.pay.biz.service.NotifyAccessAppLogService;
import com.github.lotus.pay.biz.service.NotifyAccessAppService;
import com.github.lotus.pay.biz.service.RefundRecordService;
import com.github.lotus.pay.biz.service.TradeService;
import com.github.lotus.pay.biz.support.payment.PaymentHelper;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入应用表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccessAppServiceImpl extends AbstractServiceImpl<AccessAppMapper, AccessApp> implements AccessAppService {
    private final NotifyAccessAppService notifyAccessAppService;
    private final NotifyAccessAppLogService notifyAccessAppLogService;
    private final AllPaymentService allPaymentService;
    private final TradeService tradeService;
    private final AccessAppMapping mapping;
    private final RefundRecordService refundRecordService;

    @Override
    public Optional<AccessApp> getByEncoding(String encoding) {
        return lambdaQuery().eq(AccessApp::getEncoding, encoding).oneOpt();
    }

    @Override
    public void sendAsyncNotifyApp(Long notifyAccessAppId) {
        final LocalDateTime now = LocalDateTime.now();
        final NotifyAccessApp notifyAccessApp = notifyAccessAppService.getById(notifyAccessAppId);
        ValidUtils.notNull(notifyAccessApp, "未找到对应的通知");

        final String requestSn = notifyAccessApp.getRequestSn();
        NotifyType notifyType = PaymentHelper.toNotifyType(notifyAccessApp.getNotifyType());
        NotifyAppAsyncVo<Object> data = new NotifyAppAsyncVo<>();

        String notifyUrl;

        switch (notifyType) {
            case Pay: {
                final Trade trade = tradeService.getByTradeSn(requestSn).orElseThrow(() -> ServiceException.wrap("未找到交易单据"));
                notifyUrl = trade.getNotifyUrl();
                if (Strings.isBlank(notifyUrl)) {
                    log.info("交易单据:[{}]无需进行通知, 没有设置通知地址", requestSn);
                    return;
                }
                final QueryAsyncVo<TradeStatusSync> query = allPaymentService.queryTrade(requestSn);

                data.setData(query.getData()).setPlatformType(query.getPlatformType());
                break;
            }
            case Refund: {
                final RefundRecord refund = refundRecordService.getByRefundSn(requestSn)
                    .orElseThrow(() -> ServiceException.wrap("未找到退款单据"));
                notifyUrl = refund.getNotifyUrl();
                if (Strings.isBlank(notifyUrl)) {
                    log.info("退款单据:[{}]无需进行通知, 没有设置通知地址", requestSn);
                    return;
                }

                final QueryAsyncVo<RefundStatusSync> query = allPaymentService.queryRefund(requestSn);
                data.setData(query.getData()).setPlatformType(query.getPlatformType());
                break;
            }
            default:
                throw new IllegalArgumentException("通知类型错误");
        }

        data.setNotifyAt(now).setNotifyId(notifyAccessAppId)
            .setNotifyType(notifyType.getCode());

        LocalDateTime finishAt;
        final NotifyAccessAppLog entity = new NotifyAccessAppLog()
            .setNotifyAccessAppId(notifyAccessAppId)
            .setNotifyBody(JSONUtil.toJsonStr(data))
            .setCreatedAt(now);
        try {
            final String result = new RestTemplate().postForObject(notifyUrl, data, String.class);
            if (PaymentHelper.SUCCESS_NOTIFY_FLAG.equalsIgnoreCase(result)) {
                entity.setNotifyResult(NotifyResult.Success.getCode());
            } else {
                entity.setNotifyResult(NotifyResult.Fail.getCode());
            }
        } catch (RuntimeException e) {
            entity.setNotifyResult(NotifyResult.Timeout.getCode());
        } catch (Exception e) {
            entity.setNotifyResult(NotifyResult.Fail.getCode());
        } finally {
            finishAt = LocalDateTime.now();
            entity.setUpdatedAt(finishAt);
            notifyAccessAppLogService.validInsert(entity);
        }
        final List<NotifyAccessAppLog> notifyAccessAppLogs = notifyAccessAppLogService.listByNotifyAccessAppId(notifyAccessAppId);

        if (notifyAccessAppLogs.size() >= PaymentHelper.MAX_NOTIFY_COUNT) {
            final NotifyAccessApp update = new NotifyAccessApp().setId(notifyAccessAppId).setFinishAt(finishAt).setUpdatedAt(finishAt);
            notifyAccessAppService.updateById(update);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(AccessAppInsertRo ro) {
        AccessApp entity = mapping.asAccessApp(ro);
        validInsert(entity);
    }
}
