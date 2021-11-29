package in.hocg.rabbit.bmw.biz.service;

import in.hocg.rabbit.bmw.biz.entity.AccountFlow;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [支付模块] 账户流水表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface AccountFlowService extends AbstractService<AccountFlow> {

    /**
     * 退款流水[入账]
     *
     * @param refundRecordId
     * @return
     */
    AccountFlow createInRefundFlow(Long refundRecordId);

    /**
     * 交易流水[出账]
     *
     * @param tradeOrderId
     * @return
     */
    AccountFlow createTradeFlow(Long tradeOrderId);
}
