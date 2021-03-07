package com.github.lotus.pay.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.biz.entity.PayRecord;
import com.github.lotus.pay.biz.pojo.ro.PayRecordPagingRo;
import com.github.lotus.pay.biz.pojo.vo.PayRecordOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [支付网关] 支付记录表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface PayRecordService extends AbstractService<PayRecord> {

    List<PayRecord> listByTradeId(Long tradeId);

    IPage<PayRecordOrdinaryVo> pagingByTradeId(Long tradeId, PayRecordPagingRo ro);
}
