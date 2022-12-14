package in.hocg.rabbit.mall.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.rabbit.pay.api.PayServiceApi;
import in.hocg.rabbit.pay.api.enums.SyncAccessMchTaskType;
import in.hocg.rabbit.pay.api.pojo.ro.CreateTradeRo;
import in.hocg.rabbit.pay.api.pojo.ro.GetCashierRo;
import in.hocg.rabbit.pay.api.pojo.ro.GoRefundRo;
import in.hocg.rabbit.pay.api.pojo.vo.TradeStatusSyncVo;
import in.hocg.rabbit.com.api.UniqueCodeServiceApi;
import in.hocg.rabbit.com.api.UserAddressServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.com.api.enums.UserAddressType;
import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.common.basic.PageUrlHelper;
import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.mall.api.enums.order.*;
import in.hocg.rabbit.mall.biz.convert.OrderConvert;
import in.hocg.rabbit.mall.biz.entity.*;
import in.hocg.rabbit.mall.biz.mapper.OrderMapper;
import in.hocg.rabbit.mall.biz.mapstruct.OrderItemMapping;
import in.hocg.rabbit.mall.biz.mapstruct.OrderItemSkuMapping;
import in.hocg.rabbit.mall.biz.mapstruct.OrderMapping;
import in.hocg.rabbit.mall.biz.pojo.dto.PayedDto;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.pojo.vo.*;
import in.hocg.rabbit.mall.biz.service.*;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.rabbit.mall.biz.state.order.OrderStateMachine;
import in.hocg.rabbit.mall.biz.support.mode.Discount;
import in.hocg.rabbit.mall.biz.support.mode.DiscountResult;
import in.hocg.rabbit.mall.biz.support.mode.OrderContext;
import in.hocg.rabbit.mall.biz.support.mode.OrderSupport;
import in.hocg.rabbit.mall.biz.support.mode.def.discount.Discounts;
import in.hocg.rabbit.mall.biz.support.order.MallOrderContext;
import in.hocg.rabbit.mall.biz.support.order.MallHelper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * [????????????] ???????????? ???????????????
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Slf4j
@Service
@UseConvert(OrderConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderServiceImpl extends AbstractServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapping mapping;
    private final OrderItemMapping orderItemMapping;
    private final OrderItemSkuMapping orderItemSkuMapping;
    private final OrderConvert convert;
    private final OrderItemService orderItemService;
    private final OrderDiscountService orderDiscountService;
    private final PayServiceApi bmwServiceApi;
    private final OrderMaintainService orderMaintainService;
    private final OrderDeliveryService orderDeliveryService;
    private final OrderPayRecordService orderPayRecordService;
    private final UserCouponService userCouponService;
    private final SkuService skuService;
    private final ProductService productService;
    private final UniqueCodeServiceApi snCodeServiceApi;
    private final UserAddressServiceApi userAddressServiceApi;

    @Override
    public IPage<OrderOrdinaryVo> paging(OrderPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asOrderOrdinaryVo);
    }

    @Override
    public OrderComplexVo getComplex(Long id) {
        return convert.asOrderComplexVo(getById(id));
    }

    @Override
    public void delete(DeleteRo ro) {
        removeBatchByIds(ro.getId());
    }

    @Override
    public void updateOne(Long id, UpdateOrderRo ro) {
        Order update = mapping.asOrder(ro);
        update.setId(id);
        updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundByBuyer(Long id, RefundOrderClientRo ro) {
        Order order = getByIdAndOwnerUser(id, ro.getOperatorId()).orElseThrow(() -> ServiceException.wrap("???????????????"));
        OrderStateMachine.refundByBuyer(order, ro);

        // ???????????????
        handleCancelOrClosedOrderAfter(id);

        // ????????????
        GoRefundRo goRefundRo = new GoRefundRo();
        String orderNo = order.getEncoding();
        goRefundRo.setOutTradeNo(orderNo);
        goRefundRo.setRefundAmt(order.getTotalRealAmt());
        goRefundRo.setOutRefundNo(orderNo);
        goRefundRo.setReason(StrUtil.format("?????????????????????????????????{}", orderNo));
        bmwServiceApi.goRefund(goRefundRo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundBySeller(Long id, RefundOrderManageRo ro) {
        Order entity = ValidUtils.notNull(getById(id), "???????????????");
        OrderStateMachine.refundBySeller(entity);

        // ???????????????
        handleCancelOrClosedOrderAfter(id);

        // ????????????
        GoRefundRo goRefundRo = new GoRefundRo();
        String orderNo = entity.getEncoding();
        BigDecimal totalRealAmt = entity.getTotalRealAmt();
        goRefundRo.setOutTradeNo(orderNo);
        goRefundRo.setRefundAmt(totalRealAmt);
        goRefundRo.setOutRefundNo(orderNo);
        goRefundRo.setReason(StrUtil.format("?????????????????????????????????{}", orderNo));
        bmwServiceApi.goRefund(goRefundRo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shipped(Long id, ShippedOrderBySellerRo ro) {
        Order entity = ValidUtils.notNull(getById(id), "???????????????");

        // 1. ????????????
        OrderStateMachine.shipped(entity);

        // 2. ??????????????????
        orderDeliveryService.create(id, ro);

        // 3. ??????????????????
        Order update = new Order();
        update.setId(id);
        update.setPlanReceiveAt(LocalDateTime.now().plusDays(15));
        updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receivedByBuyer(Long id, ReceivedOrderClientRo ro) {
        Order order = getByIdAndOwnerUser(id, ro.getOperatorId()).orElseThrow(() -> ServiceException.wrap("???????????????"));
        OrderStateMachine.receivedByBuyer(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeBySeller(Long id, CloseOrderManageRo ro) {
        Order entity = ValidUtils.notNull(getById(id), "???????????????");
        OrderStateMachine.closeBySeller(entity);

        // ???????????????
        handleCancelOrClosedOrderAfter(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeByBuyer(Long id, CloseOrderClientRo ro) {
        Order order = getByIdAndOwnerUser(id, ro.getOperatorId()).orElseThrow(() -> ServiceException.wrap("???????????????"));
        OrderStateMachine.closeByBuyer(order);

        // ???????????????
        handleCancelOrClosedOrderAfter(id);
    }

    @Override
    public String getCashierUrl(String orderNo) {
        Order order = getByOrderNo(orderNo).orElseThrow(() -> ServiceException.wrap("???????????????"));
        Long orderId = order.getId();

        // ????????????
        BigDecimal totalPayAmt = order.getTotalPayAmt();
        String notifyUrl = PageUrlHelper.getMallOrderPayedCallbackUrl();
        CreateTradeRo createTradeRo = new CreateTradeRo();
        createTradeRo.setOutTradeNo(orderNo);
        createTradeRo.setNotifyUrl(notifyUrl);
        createTradeRo.setTradeAmt(totalPayAmt);
        createTradeRo.setAccessCode(GlobalConstant.BMW_ACCESS_CODE);
        TradeStatusSyncVo trade = bmwServiceApi.createTrade(createTradeRo);

        Order update = new Order().setTradeNo(trade.getTradeNo());
        update.setId(orderId);
        this.updateById(update);

        GetCashierRo cashierRo = new GetCashierRo();
        cashierRo.setAccessCode(GlobalConstant.BMW_ACCESS_CODE);
        cashierRo.setOutTradeNo(orderNo);
        return bmwServiceApi.getCashierUrl(cashierRo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CalcOrderVo calcOrder(CalcOrderRo ro) {
        CalcOrderVo result = new CalcOrderVo();
        final Long userId = ro.getUserId();
        List<Serializable> useCoupons = Lists.newArrayList();
        final List<UserCoupon> userCoupons = Lists.newArrayList();

        final Long useCouponId = ro.getUseCouponId();
        LangUtils.callIfNotNull(useCouponId, useCoupons::add);

        if (Objects.nonNull(userId)) {
            UserAddressFeignVo defAddress = userAddressServiceApi.getDefaultByUserIdAndType(userId, UserAddressType.Receiver.getCodeStr());
            result.setDefaultAddress(mapping.asCalcOrderVoUserAddressVo(defAddress));
            userCoupons.addAll(userCouponService.listByAvailableAndOwnerUserId(userId));
        }

        // ????????????????????????
        List<MutablePair<CalcOrderVo.OrderItem, OrderSupport.Item>> orderItems = Lists.newArrayList();
        List<CalcOrderRo.Item> itemsRo = ro.getItems();
        for (int i = 0; i < itemsRo.size(); i++) {
            CalcOrderRo.Item item = itemsRo.get(i);
            final Long skuId = item.getSkuId();
            final Sku sku = ValidUtils.notNull(skuService.getById(skuId), "??????????????????");
            final Long productId = sku.getProductId();
            final Product product = ValidUtils.notNull(productService.getById(productId), "???????????????");
            ValidUtils.isTrue(product.getPublishedFlag(), "???????????????");
            CalcOrderVo.OrderItem left = MallHelper.asItem(item, product, sku);
            orderItems.add(MutablePair.of(left, new OrderSupport.Item(i, left.getTotalAmt())));
        }
        List<OrderSupport.Item> supportItems = orderItems.stream().map(MutablePair::getRight)
            .collect(Collectors.toUnmodifiableList());
        OrderSupport orderSupport = OrderSupport.create(supportItems);

        // TODO ????????????, ???????????????
        BigDecimal expressAmt = BigDecimal.ZERO;

        // ????????????, ???????????????(??????????????????????????????????????????????????????????????????????????????)
        List<Discount<MallOrderContext>> discounts = userCoupons.stream().map(MallHelper::getDiscountByUserCoupon)
            .collect(Collectors.toList());

        // ??????????????????
        MallOrderContext useContext = orderSupport.use(MallHelper.createContext(), discounts, useCoupons);
        orderItems.forEach(pair -> {
            Serializable id = pair.getRight().getId();
            pair.getLeft().setDiscountAmt(useContext.getDiscountAmtByOrderItem(id));
            pair.getLeft().setRealAmt(useContext.getRealAmtByOrderItem(id));
        });

        result.setTotalSaleAmt(useContext.getTotalSaleAmtByOrder());
        result.setExpressAmt(expressAmt);
        result.setDiscountAmt(useContext.getDiscountAmtByOrder());
        result.setTotalRealAmt(result.getTotalSaleAmt().add(result.getExpressAmt()));
        result.setTotalPayAmt(result.getTotalRealAmt().subtract(result.getDiscountAmt()));
        result.setItems(orderItems.stream().map(MutablePair::getLeft).collect(Collectors.toUnmodifiableList()));

        List<DiscountResult> discountResults = useContext.getDiscountResults();
        result.setUsedDiscounts(discountResults.stream().map(MallHelper::asDiscountVo)
            .collect(Collectors.toList()));

        Map<Long, UserCoupon> userCouponMaps = LangUtils.toMap(userCoupons, CommonEntity::getId);
        result.setUserCoupons(discountResults.stream().filter(MallHelper::isUserCoupon)
            .map(item -> MallHelper.asUserCoupon(userCouponMaps, item)).collect(Collectors.toList()));
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(CreateOrderRo ro) {
        final Long currentUserId = ro.getUserId();
        final CalcOrderVo.UserAddressVo receiver = ro.getReceiver();
        final String remark = ro.getRemark();

        final CalcOrderVo calcResult = this.calcOrder(ro);

        String orderNo = snCodeServiceApi.getUniqueCode(CodeType.Order.getCodeStr());
        LocalDateTime now = LocalDateTime.now();
        final Order order = mapping.asOrder(calcResult)
            .setEncoding(orderNo)
            .setOwnerUserId(currentUserId)
            .setOrderStatus(OrderStatus.Create.getCodeStr())
            .setPlanCloseAt(now.plusDays(15))
            .setRemark(remark)
            // ???????????????
            .setReceiverName(receiver.getName())
            .setReceiverTel(receiver.getTel())
            .setReceiverPostcode(receiver.getPostcode())
            .setReceiverProvince(receiver.getProvince())
            .setReceiverCity(receiver.getCity())
            .setReceiverRegion(receiver.getRegion())
            .setReceiverAddress(receiver.getAddress())
            .setReceiverAdcode(receiver.getAdcode());

        validInsert(order);
        final Long orderId = order.getId();

        // ??????????????????(?????????????????????????????????????????????)
        final List<CalcOrderVo.DiscountVo> discounts = calcResult.getUsedDiscounts();
        orderDiscountService.usedDiscount(orderId, discounts);

        List<CalcOrderVo.OrderItem> items = calcResult.getItems();
        orderItemService.saveOrderItemByOrderId(orderId, items);

        return orderNo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adjustment(Long id, AdjustmentOrderBySellerRo ro) {
        BigDecimal adjustmentAmt = ro.getAdjustmentAmt();
        Order order = Assert.notNull(getById(id), "???????????????");
        Assert.isTrue(OrderPayStatus.NotPayed.eq(order.getPayStatus()), "????????????????????????????????????");
        List<OrderItem> orderItems = orderItemService.listByOrderId(id);

        List<OrderSupport.Item> items = orderItems.stream().map(MallHelper::asItem).collect(Collectors.toList());
        OrderSupport orderSupport = OrderSupport.create(items);
        OrderContext useContext = orderSupport.use(MallHelper.createContext(), List.of(Discounts.createAdjustmentDiscount(1L, adjustmentAmt)));

        // ????????????
        Order orderUpdate = new Order();
        orderUpdate.setId(id);
        orderUpdate.setAdjustmentAmt(adjustmentAmt);
        orderUpdate.setTotalRealAmt(useContext.getTotalRealAmtByOrder());
        orderUpdate.setTotalPayAmt(orderUpdate.getTotalRealAmt().add(order.getExpressAmt()));
        Assert.isTrue(updateById(orderUpdate), "??????????????????");

        // ??????????????????
        List<OrderItem> orderItemUpdate = useContext.convertForOrderItem(item -> {
            Serializable itemId = item.getId();
            OrderItem result = new OrderItem();
            result.setId((Long) itemId);
            return result.setRealAmt(useContext.getRealAmtByOrderItem(itemId))
                .setDiscountAmt(useContext.getDiscountAmtByOrderItem(itemId));
        });
        Assert.isTrue(orderItemService.updateBatchById(orderItemUpdate), "????????????????????????");
    }

    @Override
    public OrderDeliveryComplexVo getDeliveryByBuyer(Long id) {
        return orderDeliveryService.as(orderDeliveryService.getByOrderId(id), OrderDeliveryComplexVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payed(Long id, PayedOrderBySellerRo ro) {
        Order order = Assert.notNull(getById(id), "???????????????");
        payed(new PayedDto().setOrder(order).setPayWay(OrderPayWay.Manual.getCodeStr()).setFinishedAt(LocalDateTime.now()));
    }

    @Override
    public Optional<Order> getByOrderItemId(Long orderItemId) {
        return baseMapper.getByOrderItemId(orderItemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payResult(OrderPayResultRo ro) {
        Assert.isTrue(SyncAccessMchTaskType.TradeResult.eq(ro.getSyncNotifyType()));
        TradeStatusSyncVo tradeStatusSync = ro.getTradeStatusSync();
        Assert.isTrue(in.hocg.rabbit.pay.api.enums.TradeOrderStatus.Payed.eq(tradeStatusSync.getStatus()));

        String orderNo = tradeStatusSync.getOutTradeNo();
        String payType = tradeStatusSync.getPayType();
        LocalDateTime finishedAt = tradeStatusSync.getFinishedAt();
        Order order = this.getByOrderNo(orderNo).orElseThrow(() -> ServiceException.wrap("???????????????"));
        payed(new PayedDto().setOrder(order).setPayWay(payType).setFinishedAt(finishedAt));
    }

    private void payed(PayedDto dto) {
        Order order = dto.getOrder();
        LocalDateTime finishedAt = dto.getFinishedAt();
        String payWay = dto.getPayWay();

        String encoding = order.getEncoding();
        String tradeStatus = order.getTradeStatus();
        if (!OrderTradeStatus.WaitPay.eq(tradeStatus)) {
            log.warn("??????[??????={}]?????????[{}]???????????????????????????????????????", encoding, tradeStatus);
            return;
        }

        OrderStateMachine.payed(order);

        Order update = new Order();
        update.setId(order.getId());
        update.setPayAt(finishedAt);
        update.setPayWay(payWay);
        updateById(update);
    }

    private Optional<Order> getByOrderNo(String orderNo) {
        return lambdaQuery().eq(Order::getEncoding, orderNo).oneOpt();
    }


    /**
     * ????????????????????????????????????????????????
     * - ????????????
     * - ?????????????????????
     *
     * @param orderId
     */
    private void handleCancelOrClosedOrderAfter(@NonNull Long orderId) {
        final Order order = baseMapper.selectById(orderId);
        ValidUtils.notNull(order, "???????????????");

        // ????????????????????????
        orderItemService.refundByOrderId(orderId);

        // ???????????????
        orderDiscountService.refundDiscountByOrderId(orderId);
    }

    private Optional<Order> getByIdAndOwnerUser(Long orderId, Long ownerUserId) {
        return lambdaQuery().eq(Order::getId, orderId).eq(Order::getOwnerUserId, ownerUserId).oneOpt();
    }

}
