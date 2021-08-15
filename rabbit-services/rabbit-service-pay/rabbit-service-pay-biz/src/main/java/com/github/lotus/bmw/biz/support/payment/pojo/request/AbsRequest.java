package com.github.lotus.bmw.biz.support.payment.pojo.request;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.openservices.log.http.client.ServiceException;
import com.github.lotus.bmw.biz.constant.BmwConstant;
import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.common.datadict.bmw.PaymentMchType;
import com.github.lotus.common.datadict.pay.Feature;
import com.github.lotus.bmw.biz.config.PaymentProperties;
import com.google.common.collect.Maps;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.autoconfiguration.utils.web.RequestUtils;
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

    protected PaymentMchType getPaymentMchType() {
        return ICode.ofThrow(getConfigStorage().getPaymentMchType(), PaymentMchType.class);
    }

    protected String getUrlPrefix() {
        return SpringContext.getBean(PaymentProperties.class).getUrlPrefix();
    }

    protected String getPaymentNotifyUrl() {

        HashMap<String, String> var = Maps.newHashMap();
        var.put("feature", Feature.Pay.getCode());
        String uri = StrUtil.format(BmwConstant.CALLBACK_URI, var);
        return this.getUrlPrefix() + uri;
    }

    protected String getRefundNotifyUrl() {
        HashMap<String, String> var = Maps.newHashMap();
        var.put("feature", Feature.Refund.getCode());
        String uri = StrUtil.format(BmwConstant.CALLBACK_URI, var);
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
