package com.github.lotus.mall.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.api.SnCodeServiceApi;
import com.github.lotus.common.constant.SnCodePrefixConstant;
import com.github.lotus.common.datadict.common.HandleStatus;
import com.github.lotus.mall.biz.entity.Order;
import com.github.lotus.mall.biz.entity.OrderItem;
import com.github.lotus.mall.biz.entity.OrderRefundApply;
import com.github.lotus.mall.biz.mapper.OrderRefundApplyMapper;
import com.github.lotus.mall.biz.mapstruct.OrderRefundApplyMapping;
import com.github.lotus.mall.biz.pojo.ro.OrderRefundApplyPagingRo;
import com.github.lotus.mall.biz.pojo.ro.RefundApplyRo;
import com.github.lotus.mall.biz.pojo.ro.RefundHandleRo;
import com.github.lotus.mall.biz.pojo.vo.OrderRefundApplyComplexVo;
import com.github.lotus.mall.biz.service.OrderItemService;
import com.github.lotus.mall.biz.service.OrderRefundApplyService;
import com.github.lotus.mall.biz.service.OrderService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

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
    private final OrderRefundApplyMapping mapping;
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final SnCodeServiceApi snCodeServiceApi;

    @Override
    public IPage<OrderRefundApplyComplexVo> paging(OrderRefundApplyPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    public OrderRefundApplyComplexVo getComplex(Long id) {
        return this.convertComplex(getById(id));
    }

    @Override
    public void handleRefund(Long id, @Validated RefundHandleRo ro) {
        final LocalDateTime createdAt = LocalDateTime.now();
        final Long userId = ro.getUserId();
        final String handleRemark = ro.getHandleRemark();
        final Boolean isPass = ro.getIsPass();
        HandleStatus targetStatus = isPass ? HandleStatus.Success : HandleStatus.Fail;

        final OrderRefundApply entity = getById(id);
        ValidUtils.notNull(entity, "未找到申请单");
        final HandleStatus applyStatus = ICode.ofThrow(entity.getApplyStatus(), HandleStatus.class);
        if (!HandleStatus.Processing.equals(applyStatus)) {
            throw ServiceException.wrap("申请单已被处理");
        }

        final OrderRefundApply updated = new OrderRefundApply();
        updated.setId(id);
        updated.setHandleRemark(handleRemark);
        updated.setHandleAt(createdAt);
        updated.setHandlerId(userId);
        updated.setApplyStatus(targetStatus.getCodeStr());
        updated.setLastUpdatedAt(createdAt);
        updated.setLastUpdater(userId);
        validUpdateById(updated);
    }

    @Override
    public List<OrderRefundApplyComplexVo> listComplexByOrderItemId(Long orderItemId) {
        return LangUtils.toList(this.listByOrderItemId(orderItemId), this::convertComplex);
    }

    @Override
    public void applyRefund(RefundApplyRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        final Long orderItemId = ro.getOrderItemId();
        final OrderItem orderItem = orderItemService.getById(orderItemId);
        ValidUtils.notNull(orderItem, "订单商品不存在");
        final Long orderId = orderItem.getOrderId();
        final Order order = orderService.getById(orderId);
        ValidUtils.isTrue(LangUtils.equals(order.getUserId(), ro.getUserId()), "操作失败");
        if (!order.getConfirmFlag()) {
            throw ServiceException.wrap("订单当前状态无法进行退款操作");
        }

        final List<OrderRefundApply> applies = this.listByOrderItemId(orderItemId);
        if (CollUtil.isNotEmpty(applies)) {
            throw ServiceException.wrap("已进行退款申请");
        }

        OrderRefundApply apply = mapping.asOrderRefundApply(ro);
        apply.setRefundQuantity(orderItem.getQuantity());
        apply.setRefundApplyNo(snCodeServiceApi.getSnCode(SnCodePrefixConstant.MALL_REFUND_APPLY));
        apply.setApplyStatus(HandleStatus.Processing.getCodeStr());
        apply.setCreatedAt(now);
        apply.setCreator(userId);
        this.validInsert(apply);
    }

    private List<OrderRefundApply> listByOrderItemId(Long orderItemId) {
        return lambdaQuery().eq(OrderRefundApply::getOrderItemId, orderItemId).list();
    }

    private OrderRefundApplyComplexVo convertComplex(OrderRefundApply entity) {
        return mapping.asOrderRefundApplyComplexVo(entity);
    }

}
