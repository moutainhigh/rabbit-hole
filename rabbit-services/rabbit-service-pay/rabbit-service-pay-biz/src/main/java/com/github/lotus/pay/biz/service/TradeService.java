package com.github.lotus.pay.biz.service;

import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.api.pojo.ro.GoPayRo;
import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.api.pojo.vo.QueryAsyncVo;
import com.github.lotus.pay.api.pojo.vo.RefundStatusSync;
import com.github.lotus.pay.api.pojo.vo.TradeStatusSync;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.api.pojo.ro.CloseTradeRo;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import com.github.lotus.pay.biz.pojo.ro.PayMessageRo;
import com.github.lotus.pay.biz.pojo.ro.RefundMessageRo;
import com.github.lotus.pay.biz.pojo.vo.GoRefundVo;
import com.github.lotus.pay.biz.pojo.vo.WaitPayTradeVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 交易账单表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface TradeService extends AbstractService<Trade> {

    /**
     * 创建账单
     *
     * @param ro ro
     * @return
     */
    String createTrade(CreateTradeRo ro);

    /**
     * 关闭账单
     *
     * @param ro ro
     */
    void closeTrade(CloseTradeRo ro);

    /**
     * 查询交易中账单
     *
     * @param tradeSn tradeSn
     * @return
     */
    WaitPayTradeVo getPendingTradeByTradeSn(String tradeSn);

    /**
     * 查询账单
     *
     * @param tradeSn tradeSn
     * @return
     */
    Optional<Trade> getByTradeSn(String tradeSn);

    /**
     * 发起支付
     *
     * @param ro ro
     * @return
     */
    GoPayVo goPay(GoPayRo ro);

    /**
     * 发起退款
     *
     * @param ro ro
     * @return
     */
    GoRefundVo goRefund(GoRefundRo ro);

    /**
     * 查询账单
     *
     * @param tradeSn tradeSn
     * @return
     */
    QueryAsyncVo<TradeStatusSync> queryTrade(String tradeSn);

    /**
     * 查询退费
     *
     * @param refundSn refundSn
     * @return
     */
    QueryAsyncVo<RefundStatusSync> queryRefund(String refundSn);

    /**
     * 支付结果通知
     *
     * @param ro ro
     */
    void handlePayMessage(PayMessageRo ro);

    /**
     * 处理退款回调信息
     *
     * @param ro
     */
    void handleRefundMessage(RefundMessageRo ro);
}
