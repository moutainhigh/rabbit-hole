package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.biz.entity.PayRecord;
import com.github.lotus.bmw.biz.pojo.dto.CreatePayRecordDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付模块] 支付记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface PayRecordService extends AbstractService<PayRecord> {

    PayRecord createPayRecord(CreatePayRecordDto dto);
}
