package com.github.lotus.pay.biz.support.payment.pojo.request;

import cn.hutool.json.JSONUtil;
import com.aliyun.openservices.log.http.client.ServiceException;
import com.github.lotus.common.datadict.bmw.PayMode;
import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import in.hocg.boot.web.servlet.SpringServletContext;
import in.hocg.boot.web.utils.web.RequestUtils;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.PaymentService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2020/6/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Data
@Accessors(chain = true)
public abstract class AbsRequest {

    abstract ConfigStorageDto getConfigStorage();

    protected PaymentPlatform getPlatform() {
        return getConfigStorage().getPlatform();
    }

    protected String getAppid() {
        return getConfigStorage().getAppid();
    }

    protected String getHost() {
//        todo: return Env.getConfigs().getHostname();
        return "";
    }

    protected String getPaymentNotifyUrl(PayMode payMode) {
        final String platformAppid = this.getAppid();
        final String notifyUrl = "/pay";
        return String.format("%s/payment/%s", this.getHost(), notifyUrl);
    }

    protected String getRefundNotifyUrl() {
        return String.format("%s/payment/%s", this.getHost(), "refund");
    }

    protected String getClientIp() {
        return RequestUtils.getClientIp(SpringServletContext.getRequest().orElseThrow(ServiceException::new));
    }

    protected PaymentService<?> getPayService() {
        return this.getConfigStorage().getPayService();
    }

    protected void save(AbsRequest request) {
        log.info("发起请求: {}", JSONUtil.toJsonStr(request));
    }

    protected <T> T request(PaymentRequest request) {
        final PaymentService paymentService = this.getPayService();
        this.save(this);
        return (T) paymentService.request(request);
    }
}
