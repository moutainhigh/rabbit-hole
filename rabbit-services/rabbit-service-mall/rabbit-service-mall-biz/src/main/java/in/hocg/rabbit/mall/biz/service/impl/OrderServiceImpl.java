package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.rabbit.bmw.api.BmwServiceApi;
import in.hocg.rabbit.bmw.api.enums.SyncAccessMchTaskType;
import in.hocg.rabbit.bmw.api.enums.TradeOrderStatus;
import in.hocg.rabbit.bmw.api.pojo.ro.CreateTradeRo;
import in.hocg.rabbit.bmw.api.pojo.ro.GetCashierRo;
import in.hocg.rabbit.bmw.api.pojo.vo.TradeStatusSyncVo;
import in.hocg.rabbit.com.api.SnCodeServiceApi;
import in.hocg.rabbit.common.basic.PageUrlHelper;
import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.common.constant.SnCodePrefixConstant;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.mall.api.enums.CouponType;
import in.hocg.rabbit.mall.api.enums.OrderDiscountType;
import in.hocg.rabbit.mall.api.enums.OrderSource;
import in.hocg.rabbit.mall.api.enums.OrderStatus;
import in.hocg.rabbit.mall.biz.entity.Coupon;
import in.hocg.rabbit.mall.biz.entity.Order;
import in.hocg.rabbit.mall.biz.entity.OrderDiscount;
import in.hocg.rabbit.mall.biz.entity.OrderItem;
import in.hocg.rabbit.mall.biz.mapper.OrderMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.pojo.vo.*;
import in.hocg.rabbit.mall.biz.service.*;
import in.hocg.rabbit.mall.biz.support.helper.order2.DiscountHelper;
import in.hocg.rabbit.mall.biz.support.helper.order2.OrderHelper;
import in.hocg.rabbit.mall.biz.support.helper.order2.discount.Discount;
import in.hocg.rabbit.mall.biz.support.helper.order2.modal.GeneralOrder;
import in.hocg.rabbit.mall.biz.support.helper.order2.modal.GeneralProduct;
import in.hocg.rabbit.mall.biz.support.helper.order2.rule.DiscountCheckResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.boot.utils.exception.ServiceException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * [订单模块] 订单主表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderServiceImpl extends AbstractServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapping mapping;
    private final OrderItemService orderItemService;
    private final OrderDiscountService orderDiscountService;
    private final BmwServiceApi bmwServiceApi;
    private final OrderRefundApplyService orderRefundApplyService;
    private final UserCouponService userCouponService;
    private final SkuService skuService;
    private final ProductService productService;
    private final SnCodeServiceApi snCodeServiceApi;

    @Override
    public IPage<OrderComplexVo> paging(OrderPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    public OrderComplexVo getComplex(Long id) {
        return this.convertComplex(getById(id));
    }

    @Override
    public void delete(DeleteRo ro) {
        removeBatchByIds(ro.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shipped(Long id, ShippedOrderRo ro) {
        final Long userId = ro.getUserId();
        final Order order = ValidUtils.notNull(getById(id), "未找到订单");

        if (!Lists.newArrayList(OrderStatus.WaitShip.getCodeStr()).contains(order.getOrderStatus())) {
            throw ServiceException.wrap("发货失败，订单状态错误");
        }

        LocalDateTime now = LocalDateTime.now();
        final Order updated = new Order();
        updated.setId(id);
        updated.setReceiveAt(now);
        updated.setOrderStatus(OrderStatus.WaitReceipt.getCodeStr());
        updated.setLastUpdater(userId);
        updated.setLastUpdatedAt(now);
        validUpdateById(updated);

    }

    @Override
    public void updateOne(Long id, UpdateOrderRo ro) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void close(Long id, CloseOrderRo ro) {
        final Long userId = ro.getUserId();
        final Order order = ValidUtils.notNull(getById(id), "未找到订单");
        if (!Lists.newArrayList(OrderStatus.WaitPay.getCodeStr()).contains(order.getOrderStatus())) {
            throw ServiceException.wrap("取消失败，请联系客服");
        }

        final Order updated = new Order();
        updated.setId(id);
        updated.setOrderStatus(OrderStatus.Closed.getCodeStr());
        updated.setLastUpdater(userId);
        updated.setLastUpdatedAt(LocalDateTime.now());
        validUpdateById(updated);
        this.handleCancelOrClosedOrderAfter(id);

    }

    @Override
    public String getCashierUrl(String orderNo) {
        GetCashierRo cashierRo = new GetCashierRo();
        cashierRo.setAccessCode(GlobalConstant.BMW_ACCESS_CODE);
        cashierRo.setOutTradeNo(orderNo);
        return bmwServiceApi.getCashierUrl(cashierRo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CalcOrderVo calcOrder(CalcOrderRo ro) {
        LocalDateTime createdAt = LocalDateTime.now();

        final CalcOrderVo result = new CalcOrderVo();
        final Long userId = ro.getUserId();
        final Long selectedCouponId = ro.getSelectedCouponId();
        final List<CalcOrderRo.Item> items = ro.getItems();

        // 使用的优惠
        List<Discount> useDiscounts = Lists.newArrayList();

        final List<UserCouponComplexVo> userAllCoupons = LangUtils.callIfNotNull(userId, userCouponService::listComplexByUserId).orElse(Collections.emptyList());
        final List<Discount> allDiscount = Lists.newArrayList();
        final List<Discount> userAllCouponDiscount = userAllCoupons.parallelStream().map(DiscountHelper::createCoupon).collect(Collectors.toList());
        allDiscount.addAll(userAllCouponDiscount);
        final Map<Long, UserCouponComplexVo> userAllCouponMap = LangUtils.toMap(userAllCoupons, UserCouponComplexVo::getId);
        final Map<Long, Discount> userAllCouponDiscountMap = LangUtils.toMap(userAllCouponDiscount, Discount::id);
        if (Objects.nonNull(selectedCouponId)) {
            if (!userAllCouponDiscountMap.containsKey(selectedCouponId)) {
                throw ServiceException.wrap("选择优惠券不可用");
            } else {
                useDiscounts.add(userAllCouponDiscountMap.get(selectedCouponId));
            }
        }

        // 1. 检查商品
        Map<Long, SkuComplexVo> skuMap = Maps.newHashMap();
        Map<Long, ProductComplexVo> productMap = Maps.newHashMap();

        List<GeneralProduct> products = Lists.newArrayList();
        for (CalcOrderRo.Item item : items) {
            final Long skuId = item.getSkuId();
            final SkuComplexVo sku = ValidUtils.notNull(skuService.getComplexById(skuId), "商品规格错误");
            final Long productId = sku.getProductId();
            final ProductComplexVo product = ValidUtils.notNull(productService.getComplexById(productId), "未找到商品");
            ValidUtils.isTrue(product.getPublishedFlag(), "商品已下架");
            skuMap.put(skuId, sku);
            productMap.put(productId, product);

            products.add(new GeneralProduct(sku.getUnitPrice(), item.getQuantity())
                .setProductCategoryId(product.getProductCategoryId())
                .setProductSkuId(sku.getId()).setProductId(product.getId()));
        }

        // 2. 订单内容
        GeneralOrder generalOrder = new GeneralOrder(products, userId, OrderSource.Unknown.getCodeStr(), createdAt);

        // 3. 使用已选中优惠
        generalOrder = OrderHelper.use(generalOrder, useDiscounts);
        final Map<Discount, BigDecimal> allUseDiscount = generalOrder.getAllUseDiscount();
        final List<Serializable> useCouponIds = allUseDiscount.keySet().parallelStream()
            .filter(discount -> discount instanceof Coupon)
            .map(Discount::id).collect(Collectors.toList());

        // 如果用户选中的优惠券没有生效的话
        if (Objects.nonNull(selectedCouponId) && !useCouponIds.contains(selectedCouponId)) {
            throw ServiceException.wrap("选择优惠券不可用");
        }

        // 4. 检查剩下的优惠
        final Map<Discount, DiscountCheckResult> checkResults = OrderHelper.check2(generalOrder, allDiscount);
        final Map<Long, DiscountCheckResult> allUserCouponMap = checkResults.entrySet().parallelStream()
            .filter(entry -> entry.getKey() instanceof Coupon)
            .map(Map.Entry::getValue)
            .collect(Collectors.toMap((dcr) -> dcr.getDiscount().id(), o -> o));

        // 用户所有优惠券是否可用
        final List<CalcOrderVo.CouponVo> couponVos = allUserCouponMap.values().parallelStream()
            .map(dcr -> {
                final Discount discount = dcr.getDiscount();
                final Long id = discount.id();
                final UserCouponComplexVo complexVo = userAllCouponMap.get(id);
                final String couponSn = complexVo.getCouponNo();
                final LocalDateTime startAt = complexVo.getStartAt();
                final LocalDateTime endAt = complexVo.getEndAt();
                final String instructions = complexVo.getUseInstructions();
                String condition = StrUtil.format("满¥{}可用", complexVo.getMinPoint());
                return new CalcOrderVo.CouponVo().setId(id)
                    .setCondition(condition)
                    .setTitle(complexVo.getTitle())
                    .setValueDesc(complexVo.getCredit().toString())
                    .setUseAmount(allUseDiscount.getOrDefault(discount, null))
                    .setUnitDesc(CouponType.FixedAmt.eq(complexVo.getCouponType()) ? "元" : "折")
                    .setInstructions(instructions)
                    .setEndAt(endAt)
                    .setStartAt(startAt)
                    .setCouponNo(couponSn)
                    .setReason(dcr.getFirstErrorMessage())
                    .setUsable(dcr.isOk())
                    .setSelected(LangUtils.equals(id, selectedCouponId));
            }).collect(Collectors.toList());


        final BigDecimal totalAmount = generalOrder.getTotalAmount();
        final BigDecimal payAmount = generalOrder.getPaymentAmount();
        final BigDecimal discountTotalAmount = generalOrder.getTotalDiscountAmount();

        // 5. 获取订单项信息
        List<CalcOrderVo.OrderItem> orderItems = generalOrder.mapProduct(item -> {
            final SkuComplexVo skuComplexVo = skuMap.get(item.getProductSkuId());
            final ProductComplexVo productComplexVo = productMap.get(item.getProductId());
            final String specData = skuComplexVo.getSpecData();
            return new CalcOrderVo.OrderItem()
                .setSpecData(specData)
                .setImageUrl(LangUtils.getOrDefault(skuComplexVo.getImageUrl(), productComplexVo.getMainPhotoUrl()))
                .setSkuId(item.getProductSkuId())
                .setSkuCode(skuComplexVo.getEncoding())
                .setQuantity(item.getProductQuantity())
                .setPrice(item.getProductPrice())
                .setTitle(productComplexVo.getTitle())
                .setProductId(item.getProductId())
                .setDiscountAmount(item.getDiscountAmount())
                .setRealAmount(item.getRealAmount())
                .setSpec(JSON.parseArray(specData, KeyValue.class))
                .setTotalAmount(item.getProductPrice().multiply(new BigDecimal(item.getProductQuantity())));
        });
        result.setItems(orderItems);

        // 6. 优惠信息
        List<CalcOrderVo.DiscountInfo> discounts = Lists.newArrayList();
        for (Map.Entry<Discount, BigDecimal> entry : allUseDiscount.entrySet()) {
            final Discount discount = entry.getKey();
            final BigDecimal value = entry.getValue();
            final Long id = discount.id();
            CalcOrderVo.DiscountInfo discountInfo;
            if (discount instanceof Coupon) {
                discountInfo = new CalcOrderVo.DiscountInfo()
                    .setId(id)
                    .setType(OrderDiscountType.Coupon.getCodeStr())
                    .setDiscountAmount(value);
            } else {
                throw new UnsupportedOperationException("未知的优惠信息");
            }
            discounts.add(discountInfo);
        }

        final BigDecimal couponDiscountAmount = discounts.parallelStream()
            .filter(item -> OrderDiscountType.Coupon.eq(item.getType()))
            .map(CalcOrderVo.DiscountInfo::getDiscountAmount)
            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        result.setDiscounts(discounts);
        result.setTotalAmount(totalAmount);
        result.setCouponDiscountAmount(couponDiscountAmount);
        result.setDiscountTotalAmount(discountTotalAmount);
        result.setPayAmount(payAmount);
        result.setDefaultAddress(null);
        result.setCoupons(couponVos);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(CreateOrderRo ro) {
        final Long currentUserId = ro.getUserId();
        final CreateOrderRo.Receiver receiver = ro.getReceiver();
        final Long selectedCouponId = ro.getSelectedCouponId();
        final String remark = ro.getRemark();

        final CalcOrderVo calcResult = this.calcOrder(ro);
        final List<CalcOrderVo.DiscountInfo> discounts = calcResult.getDiscounts();
        final BigDecimal discountTotalAmount = calcResult.getDiscountTotalAmount();
        final BigDecimal totalAmount = calcResult.getTotalAmount();
        final BigDecimal payAmount = calcResult.getPayAmount();
        final BigDecimal couponDiscountAmount = calcResult.getCouponDiscountAmount();

        String orderNo = snCodeServiceApi.getSnCode(SnCodePrefixConstant.MALL_ORDER);
        LocalDateTime now = LocalDateTime.now();
        final Order order = new Order()
            .setOrderNo(orderNo)
            .setUserId(currentUserId)
            .setOrderStatus(OrderStatus.WaitPay.getCodeStr())
            .setPlanConfirmAt(now.plusDays(15))
            .setRemark(remark)
            // 金额相关
            .setCouponDiscountAmt(couponDiscountAmount)
            .setFreightAmt(BigDecimal.ZERO)
            .setTotalAmt(totalAmount)
            .setUserPayAmt(payAmount)
            // 收货人信息
            .setReceiverName(receiver.getName())
            .setReceiverTel(receiver.getPhone())
            .setReceiverPostcode(receiver.getPostCode())
            .setReceiverProvince(receiver.getProvince())
            .setReceiverCity(receiver.getCity())
            .setReceiverRegion(receiver.getRegion())
            .setReceiverAddress(receiver.getAddress())
            .setReceiverAdcode(receiver.getAdcode());

        // 处理优惠信息
        if (!CollectionUtils.isEmpty(discounts)) {
            for (CalcOrderVo.DiscountInfo discount : discounts) {
                final OrderDiscountType discountType = ICode.ofThrow(discount.getType(), OrderDiscountType.class);
                if (discountType == OrderDiscountType.Coupon) {
                    final Long userCouponId = discount.getId();
                    final BigDecimal discountAmount = discount.getDiscountAmount();
                    if (!userCouponService.updateUsedStatus(userCouponId, discountAmount)) {
                        throw ServiceException.wrap("优惠券已被使用");
                    }
                    orderDiscountService.useCoupon(order.getId(), userCouponId);
                } else {
                    throw ServiceException.wrap("系统繁忙，请稍后");
                }
            }
        }
        validInsert(order);
        final Long orderId = order.getId();

        final List<CalcOrderVo.OrderItem> orderItems = calcResult.getItems();
        final List<OrderItem> orderItemList = orderItems.parallelStream().map(item -> new OrderItem()
            .setUserPayAmt(item.getRealAmount())
            .setSkuSpecData(item.getSpecData())
            .setSkuCode(item.getSkuCode())
            .setDiscountAmt(item.getDiscountAmount())
            .setTotalAmt(item.getTotalAmount())
            .setTitle(item.getTitle())
            .setQuantity(item.getQuantity())
            .setImageUrl(item.getImageUrl())
            .setUnitPrice(item.getPrice())
            .setProductId(item.getProductId())
            .setSkuId(item.getSkuId())
            .setOrderId(orderId)).collect(Collectors.toList());
        for (OrderItem orderItem : orderItemList) {
            final Long skuId = orderItem.getSkuId();
            final Integer quantity = orderItem.getQuantity();
            if (skuService.casValidAndSubtractStock(skuId, quantity)) {
                orderItemService.validInsert(orderItem);
            } else {
                throw ServiceException.wrap("库存商品不足");
            }
        }


        // 生成交易
        String notifyUrl = PageUrlHelper.getMallOrderPayedCallbackUrl();
        CreateTradeRo createTradeRo = new CreateTradeRo();
        createTradeRo.setOutTradeNo(orderNo);
        createTradeRo.setNotifyUrl(notifyUrl);
        createTradeRo.setTradeAmt(payAmount);
        createTradeRo.setAccessCode(GlobalConstant.BMW_ACCESS_CODE);
        TradeStatusSyncVo trade = bmwServiceApi.createTrade(createTradeRo);

        Order update = new Order().setTradeNo(trade.getTradeNo());
        update.setId(orderId);
        this.updateById(update);
        return orderNo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyRefund(RefundApplyRo ro) {
        orderRefundApplyService.applyRefund(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(CancelOrderRo ro) {
        final Long userId = ro.getUserId();
        final Long orderId = ro.getOrderId();
        LocalDateTime now = LocalDateTime.now();

        final Order order = ValidUtils.notNull(getById(orderId), "未找到订单");
        if (!Lists.newArrayList(OrderStatus.WaitPay.getCodeStr()).contains(order.getOrderStatus())) {
            throw ServiceException.wrap("取消失败，请联系客服");
        }

        final Order updated = new Order();
        updated.setId(orderId);
        updated.setOrderStatus(OrderStatus.Closed.getCodeStr());
        updated.setLastUpdater(userId);
        updated.setLastUpdatedAt(now);
        validUpdateById(updated);
        this.handleCancelOrClosedOrderAfter(orderId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmOrder(ConfirmOrderRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();
        Long id = ro.getOrderId();

        final Order order = this.getById(id);
        ValidUtils.notNull(order, "未找到订单");
        ValidUtils.isTrue(LangUtils.equals(order.getUserId(), userId), "非订单所有人，操作失败");

        if (!Lists.newArrayList(OrderStatus.WaitReceipt.getCode()).contains(order.getOrderStatus())) {
            throw ServiceException.wrap("确认失败，请联系客服");
        }

        final Order updated = new Order();
        updated.setId(id);
        updated.setOrderStatus(OrderStatus.Received.getCodeStr());
        updated.setConfirmFlag(true);
        updated.setReceiveAt(now);
        updated.setLastUpdater(userId);
        updated.setLastUpdatedAt(now);
        validUpdateById(updated);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payResult(OrderPayResultRo ro) {
        Assert.isTrue(SyncAccessMchTaskType.TradeResult.eq(ro.getSyncNotifyType()));
        TradeStatusSyncVo tradeStatusSync = ro.getTradeStatusSync();
        Assert.isTrue(TradeOrderStatus.Payed.eq(tradeStatusSync.getStatus()));

        String orderNo = tradeStatusSync.getOutTradeNo();
        String payType = tradeStatusSync.getPayType(); // todo
        Optional<Order> orderOpl = this.getByOrderNo(orderNo);
        if (orderOpl.isEmpty()) {
            throw ServiceException.wrap("订单不存在");
        }
        final Order order = orderOpl.get();
        if (!OrderStatus.WaitPay.eq(order.getOrderStatus())) {
            log.warn("订单[单号={}]，状态[{}]非待付款时，被调用支付成功", orderNo, order.getOrderStatus());
            return;
        }

        // 更改订单状态
        final Order update = new Order();
        update.setId(order.getId());
        update.setOrderStatus(OrderStatus.WaitShip.getCodeStr());
        update.setPayAt(LocalDateTime.now());
        update.setPayType(payType);
        validUpdateById(update);
    }

    private Optional<Order> getByOrderNo(String orderNo) {
        return lambdaQuery().eq(Order::getOrderNo, orderNo).oneOpt();
    }


    /**
     * 处理关闭订单或取消订单之后的逻辑
     * - 库存归还
     * - 优惠券状态变更
     *
     * @param orderId
     */
    private void handleCancelOrClosedOrderAfter(@NonNull Long orderId) {
        final Order order = baseMapper.selectById(orderId);
        ValidUtils.notNull(order, "订单不存在");
        // 取消订单锁定库存
        final List<OrderItem> orderItems = orderItemService.listByOrderId(orderId);
        for (OrderItem orderItem : orderItems) {
            final Long skuId = orderItem.getSkuId();
            final Integer quantity = orderItem.getQuantity();
            if (!skuService.casValidAndPlusStock(skuId, quantity)) {
                throw ServiceException.wrap("系统繁忙，请稍后");
            }
        }

        // 归还优惠券
        List<OrderDiscount> orderDiscounts = orderDiscountService.listByOrderId(orderId);
        for (OrderDiscount orderDiscount : orderDiscounts) {
            if (RefType.UserCoupon.eq(orderDiscount.getRefType())) {
                userCouponService.updateUnusedStatus(orderDiscount.getRefId());
            }
        }
    }

    private OrderComplexVo convertComplex(Order entity) {
        OrderComplexVo result = mapping.asOrderComplexVo(entity);
        result.setOrderItems(orderItemService.listComplexByOrderId(entity.getId()));
        return result;
    }
}
