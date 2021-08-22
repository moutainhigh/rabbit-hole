package com.github.lotus.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mall.biz.entity.OrderRefundApply;
import com.github.lotus.mall.biz.mapper.OrderRefundApplyMapper;
import com.github.lotus.mall.biz.pojo.ro.OrderRefundApplyPagingRo;
import com.github.lotus.mall.biz.pojo.ro.RefundHandleRo;
import com.github.lotus.mall.biz.pojo.vo.OrderRefundApplyComplexVo;
import com.github.lotus.mall.biz.service.OrderRefundApplyService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [订单模块] 退货申请 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderRefundApplyServiceImpl extends AbstractServiceImpl<OrderRefundApplyMapper, OrderRefundApply> implements OrderRefundApplyService {

    @Override
    public IPage<OrderRefundApplyComplexVo> paging(OrderRefundApplyPagingRo ro) {
        return null;
    }

    @Override
    public OrderRefundApplyComplexVo getComplex(Long id) {
        return null;
    }

    @Override
    public void handleRefund(Long id, RefundHandleRo ro) {

    }

}
