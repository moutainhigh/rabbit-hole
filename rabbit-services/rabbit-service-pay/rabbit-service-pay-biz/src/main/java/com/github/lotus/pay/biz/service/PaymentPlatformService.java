package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.biz.entity.PaymentPlatform;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import com.github.lotus.pay.biz.pojo.vo.GoRefundVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付网关] 第三方支付平台表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface PaymentPlatformService extends AbstractService<PaymentPlatform> {

    boolean closeTrade(Long accessPlatformId, String tradeSn);

    GoPayVo payTrade(GoPayRo ro);

    GoRefundVo refundTrade(GoRefundRo ro);
}
