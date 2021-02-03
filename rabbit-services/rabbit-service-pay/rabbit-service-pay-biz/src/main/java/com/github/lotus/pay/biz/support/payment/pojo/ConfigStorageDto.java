package com.github.lotus.pay.biz.support.payment.pojo;

import com.github.lotus.common.datadict.pay.PaymentPlatform;
import com.github.lotus.pay.biz.support.payment.helper.RequestHelper;
import in.hocg.payment.ConfigStorage;
import in.hocg.payment.PaymentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public class ConfigStorageDto {
    private final String appid;
    private final Long accessPlatformId;
    private final PaymentPlatform platform;
    private final ConfigStorage configStorage;

    public PaymentService<?> getPayService() {
        return RequestHelper.getPayService(this.getPlatform(), this.getConfigStorage());
    }
}
