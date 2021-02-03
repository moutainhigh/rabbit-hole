package com.github.lotus.pay.biz.support.payment.pojo.request;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.openservices.log.http.client.ServiceException;
import com.github.lotus.common.datadict.pay.Feature;
import com.github.lotus.common.datadict.pay.PaymentPlatform;
import com.github.lotus.pay.biz.constant.PaymentConstants;
import com.github.lotus.pay.biz.support.payment.PaymentProperties;
import com.github.lotus.pay.biz.support.payment.pojo.ConfigStorageDto;
import com.google.common.collect.Maps;
import in.hocg.boot.web.SpringContext;
import in.hocg.boot.web.servlet.SpringServletContext;
import in.hocg.boot.web.utils.web.RequestUtils;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.PaymentService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

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

    protected String getUrlPrefix() {
        return SpringContext.getBean(PaymentProperties.class).getUrlPrefix();
    }

    protected String getPaymentNotifyUrl() {
        PaymentPlatform platform = getPlatform();
        Long accessPlatformId = getConfigStorage().getAccessPlatformId();

        HashMap<String, String> var = Maps.newHashMap();
        var.put("feature", Feature.Pay.getCode());
        var.put("platform", platform.getCodeStr());
        var.put("accessPlatformId", String.valueOf(accessPlatformId));
        String uri = StrUtil.format(PaymentConstants.CALLBACK_URI, var);
        return this.getUrlPrefix() + uri;
    }

    protected String getRefundNotifyUrl() {
        PaymentPlatform platform = getPlatform();
        Long accessPlatformId = getConfigStorage().getAccessPlatformId();

        HashMap<String, String> var = Maps.newHashMap();
        var.put("feature", Feature.Refund.getCode());
        var.put("platform", platform.getCodeStr());
        var.put("accessPlatformId", String.valueOf(accessPlatformId));
        String uri = StrUtil.format(PaymentConstants.CALLBACK_URI, var);
        return this.getUrlPrefix() + uri;
    }

    protected String getClientIp() {
        return RequestUtils.getClientIp(SpringServletContext.getRequest().orElseThrow(ServiceException::new));
    }

    protected PaymentService<?> getPayService() {
        return this.getConfigStorage().getPayService();
    }

    protected void save(AbsRequest request) {
        String requestBody = JSONUtil.toJsonStr(request);
        log.info("发起请求: {}", requestBody);
    }

    protected <T> T request(PaymentRequest request) {
        final PaymentService paymentService = this.getPayService();
        this.save(this);
        return (T) paymentService.request(request);
    }
}
