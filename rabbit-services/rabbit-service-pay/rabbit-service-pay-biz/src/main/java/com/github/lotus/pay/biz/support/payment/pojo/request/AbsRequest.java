package com.github.lotus.pay.biz.support.payment.pojo.request;

import cn.hutool.json.JSONUtil;
import com.aliyun.openservices.log.http.client.ServiceException;
import com.github.lotus.pay.biz.enumns.PaymentPlatformType;
import com.github.lotus.pay.biz.enumns.PaymentWayType;
import com.github.lotus.pay.biz.support.payment.resolve.message.FeatureType;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.SpringContext;
import in.hocg.boot.web.servlet.SpringServletContext;
import in.hocg.boot.web.utils.web.RequestUtils;
import in.hocg.payment.PaymentRequest;
import in.hocg.payment.PaymentService;
import in.hocg.payment.alipay.v2.AliPayService;
import in.hocg.payment.alipay.v2.response.AliPayHttpResponse;
import in.hocg.payment.wxpay.v2.WxPayService;
import in.hocg.payment.wxpay.v2.response.WxPayXmlResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2020/6/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public abstract class AbsRequest {

    /**
     * 支付平台
     *
     * @return
     */
    protected abstract PaymentPlatformType getPaymentPlatform();

    /**
     * 支付平台唯一标识
     *
     * @return
     */
    protected abstract String getPlatformAppid();

    protected String getHost() {
//        todo: return Env.getConfigs().getHostname();
        return "";
    }

    protected String getPaymentNotifyUrl(PaymentWayType paymentWay) {
        final String platformAppid = this.getPlatformAppid();
        final String notifyUrl = paymentWay.getNotifyUrl(FeatureType.Payment, platformAppid);
        return String.format("%s/payment/%s", this.getHost(), notifyUrl);
    }

    protected String getRefundNotifyUrl(PaymentWayType paymentWay) {
        final String platformAppid = this.getPlatformAppid();
        final String notifyUrl = paymentWay.getNotifyUrl(FeatureType.Refund, platformAppid);
        return String.format("%s/payment/%s", this.getHost(), notifyUrl);
    }

    protected String getClientIp() {
        return RequestUtils.getClientIp(SpringServletContext.getRequest().orElseThrow(ServiceException::new));
    }

    protected PaymentService getPayService() {
        final PaymentPlatformType paymentPlatform = this.getPaymentPlatform();
        final String appid = this.getPlatformAppid();
        ValidUtils.notNull(appid, "支付平台唯一标识错误");
        switch (paymentPlatform) {
            case AliPay:
                return SpringContext.getBean(AliPayService.class);
            case WxPay:
                return SpringContext.getBean(WxPayService.class);
            default:
                throw new UnsupportedOperationException();
        }
    }

    protected <T> T request(PaymentRequest request) {
        final PaymentService paymentService = this.getPayService();
        this.save(this);
        return (T) paymentService.request(request);
    }

    protected boolean isSuccess(WxPayXmlResponse response) {
        return "SUCCESS".equalsIgnoreCase(response.getResultCode());
    }

    protected boolean isSuccess(AliPayHttpResponse response) {
        return "10000".equalsIgnoreCase(response.getCode());
    }

    protected void save(AbsRequest request) {
        log.info("发起请求: {}", JSONUtil.toJsonStr(request));
    }
}
