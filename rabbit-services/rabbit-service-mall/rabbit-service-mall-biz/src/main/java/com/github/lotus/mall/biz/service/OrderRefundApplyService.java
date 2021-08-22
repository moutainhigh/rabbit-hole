package com.github.lotus.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.OrderRefundApply;
import com.github.lotus.mall.biz.pojo.ro.OrderRefundApplyPagingRo;
import com.github.lotus.mall.biz.pojo.ro.RefundHandleRo;
import com.github.lotus.mall.biz.pojo.vo.OrderRefundApplyComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [订单模块] 退货申请 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface OrderRefundApplyService extends AbstractService<OrderRefundApply> {

    IPage<OrderRefundApplyComplexVo> paging(OrderRefundApplyPagingRo ro);

    OrderRefundApplyComplexVo getComplex(Long id);

    void handleRefund(Long id, RefundHandleRo ro);
}
