package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.biz.entity.PaymentMchRecord;
import com.github.lotus.bmw.biz.pojo.dto.PaymentMchRecordDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付模块] 支付商户业务记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface PaymentMchRecordService extends AbstractService<PaymentMchRecord> {

    PaymentMchRecord create(PaymentMchRecordDto dto);
}
