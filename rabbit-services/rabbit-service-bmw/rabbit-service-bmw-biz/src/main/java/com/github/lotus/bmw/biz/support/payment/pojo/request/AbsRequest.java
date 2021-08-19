package com.github.lotus.bmw.biz.support.payment.pojo.request;

import cn.hutool.core.util.StrUtil;
import com.aliyun.openservices.log.http.client.ServiceException;
import com.github.lotus.bmw.biz.constant.BmwConstant;
import com.github.lotus.bmw.biz.support.PageUrlHelper;
import com.github.lotus.bmw.biz.support.payment.ConfigStorageDto;
import com.github.lotus.common.datadict.bmw.PaymentMchType;
import com.google.common.collect.Maps;
import in.hocg.boot.utils.enums.ICode;
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
        return ICode.ofThrow(getConfigStorage().getPaymentMch().getType(), PaymentMchType.class);
    }

    protected String getUrlPrefix() {
        return PageUrlHelper.getHostname();
    }

    protected String getPaymentNotifyUrl(Long payRecordId) {
        HashMap<String, Object> var = Maps.newHashMap();
        var.put("paymentMchType", getPaymentMchType().getCodeStr());
        var.put("paymentMchCode", getConfigStorage().getPaymentMch().getEncoding());
        var.put("payRecordId", payRecordId);
        String uri = StrUtil.format(BmwConstant.PAY_CALLBACK_URI, var);
        return this.getUrlPrefix() + uri;
    }

    protected String getRefundNotifyUrl() {
        HashMap<String, String> var = Maps.newHashMap();
        var.put("paymentMchType", getPaymentMchType().getCodeStr());
        var.put("paymentMchCode", getConfigStorage().getPaymentMch().getEncoding());
        String uri = StrUtil.format(BmwConstant.REFUND_CALLBACK_URI, var);
        return this.getUrlPrefix() + uri;
    }

    protected String getClientIp() {
        return RequestUtils.getClientIp(SpringServletContext.getRequest().orElseThrow(ServiceException::new));
    }

    protected PaymentService<?> getPayService() {
        return this.getConfigStorage().getPayService();
    }

    protected <T> T request(PaymentRequest request) {
        final PaymentService paymentService = this.getPayService();
        return (T) paymentService.request(request);
    }

}
