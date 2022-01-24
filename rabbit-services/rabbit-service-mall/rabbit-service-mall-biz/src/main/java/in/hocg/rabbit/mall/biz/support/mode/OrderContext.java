package in.hocg.rabbit.mall.biz.support.mode;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import in.hocg.rabbit.mall.biz.support.mode.utils.MathUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Accessors(chain = true)
public abstract class OrderContext {
    @Getter
    @ApiModelProperty("订单")
    private OrderSupport order;
    @Getter
    @ApiModelProperty("所有优惠信息")
    private List<? extends Discount<? extends OrderContext>> discounts;
    @Getter
    @ApiModelProperty("优惠结果")
    private final Map<Serializable, DiscountResult> discountResultMap = Maps.newHashMap();
    @Getter
    @ApiModelProperty("[优惠券,[商品,优惠金额]]")
    private final Map<Serializable, Map<Serializable, BigDecimal>> discountMaps = Maps.newHashMap();

    @ApiModelProperty("获取所有优惠匹配结果")
    public List<DiscountResult> getDiscountResults() {
        return new ArrayList<>(discountResultMap.values());
    }

    @ApiModelProperty("获取优惠匹配结果")
    public DiscountResult getDiscountResult(Serializable discountId) {
        return discountResultMap.get(discountId);
    }

    @ApiOperation("获取订单明细")
    public List<OrderSupport.Item> getOrderItem() {
        return this.getOrder().getItems();
    }

    @ApiOperation("获取指定订单明细")
    public Optional<OrderSupport.Item> getOrderItem(Serializable orderItemId) {
        return getOrderItem().stream()
            .filter(item -> ObjectUtil.equal(item.getId(), orderItemId))
            .findFirst();
    }

    @ApiOperation("原价 - 订单明细")
    public BigDecimal getTotalAmtByOrderItem(Serializable orderItemId) {
        return getOrderItem(orderItemId).map(OrderSupport.Item::getTotalAmt)
            .orElseThrow(() -> new RuntimeException("没有找到订单明细"));
    }

    @ApiOperation("优惠后金额 - 订单明细( = 原总价 - 优惠金额)")
    public BigDecimal getRealAmtByOrderItem(Serializable orderItemId) {
        return getOrderItem(orderItemId)
            .map(item -> item.getTotalAmt().subtract(getDiscountAmtByOrderItem(orderItemId)))
            .orElseThrow(() -> new RuntimeException("没有找到订单明细"));
    }

    @ApiOperation("获取指定明细优惠金额")
    public BigDecimal getDiscountAmtByOrderItem(Serializable orderItemId) {
        return discountMaps.values().stream()
            .map(entry -> entry.getOrDefault(orderItemId, BigDecimal.ZERO))
            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @ApiOperation("订单销售总额")
    public BigDecimal getTotalSaleAmtByOrder() {
        return getOrderItem().stream()
            .map(OrderSupport.Item::getTotalAmt)
            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @ApiOperation("订单优惠总额")
    public BigDecimal getDiscountAmtByOrder() {
        return discountMaps.values().stream().flatMap(entry -> entry.values().stream())
            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @ApiOperation("实际订单总额 = 销售总额 - 优惠金额")
    public BigDecimal getTotalRealAmtByOrder() {
        return getTotalSaleAmtByOrder().subtract(getDiscountAmtByOrder());
    }

    @ApiOperation("获取总优惠金额(根据用户使用的匹配条件)")
    public BigDecimal getTotalDiscountAmtByDiscount(Function<? super Discount<? extends OrderContext>, Boolean> match) {
        return getUsedDiscount().stream()
            .filter(match::apply).map(Discount::getDiscountAmt)
            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @ApiOperation("使用的优惠信息")
    public List<? extends Discount<? extends OrderContext>> getUsedDiscount() {
        return convertForUsedDiscountResult(DiscountResult::getDiscount);
    }

    @ApiOperation("未使用优惠信息")
    public List<? extends Discount<? extends OrderContext>> getNoUsedDiscount() {
        return convertForNoUsedDiscountResult(DiscountResult::getDiscount);
    }

    @ApiOperation("可用的优惠信息")
    public List<? extends Discount<? extends OrderContext>> getAvailableDiscount() {
        return convertForAvailableDiscountResult(DiscountResult::getDiscount);
    }

    @ApiOperation("剩余可用的优惠信息")
    public List<? extends Discount<? extends OrderContext>> getSurplusAvailableDiscount() {
        return convertForSurplusAvailableDiscountResult(DiscountResult::getDiscount);
    }

    @ApiOperation("不可用优惠信息")
    public List<? extends Discount<? extends OrderContext>> getNoAvailableDiscount() {
        return convertForNoAvailableDiscountResult(DiscountResult::getDiscount);
    }

    @ApiOperation("转换器(订单明细) - OrderItem")
    public <R> List<R> convertForOrderItem(Function<? super OrderSupport.Item, ? extends R> mapper) {
        return getOrderItem().stream().map(mapper).collect(Collectors.toList());
    }

    @ApiOperation("转换器(所有优惠信息) - Discount")
    public <R> List<R> convertForDiscount(Function<? super Discount<? extends OrderContext>, ? extends R> mapper) {
        return convertForDiscountResult(discountResult -> mapper.apply(discountResult.getDiscount()));
    }

    @ApiOperation("转换器(使用优惠信息) - Discount")
    public <R> List<R> convertForUsedDiscount(Function<? super Discount<? extends OrderContext>, ? extends R> mapper) {
        return convertForUsedDiscountResult(discountResult -> mapper.apply(discountResult.getDiscount()));
    }

    @ApiOperation("转换器(未使用优惠信息) - Discount")
    public <R> List<R> convertForNoUsedDiscount(Function<? super Discount<? extends OrderContext>, ? extends R> mapper) {
        return convertForNoUsedDiscountResult(discountResult -> mapper.apply(discountResult.getDiscount()));
    }

    @ApiOperation("转换器(可用优惠信息) - Discount")
    public <R> List<R> convertForAvailableDiscount(Function<? super Discount<? extends OrderContext>, ? extends R> mapper) {
        return convertForAvailableDiscountResult(discountResult -> mapper.apply(discountResult.getDiscount()));
    }

    @ApiOperation("转换器(剩余可用优惠信息) - Discount")
    public <R> List<R> convertForSurplusAvailableDiscount(Function<? super Discount<? extends OrderContext>, ? extends R> mapper) {
        return convertForSurplusAvailableDiscountResult(discountResult -> mapper.apply(discountResult.getDiscount()));
    }

    @ApiOperation("转换器(不可用优惠信息) - Discount")
    public <R> List<R> convertForNoAvailableDiscount(Function<? super Discount<? extends OrderContext>, ? extends R> mapper) {
        return convertForNoAvailableDiscountResult(discountResult -> mapper.apply(discountResult.getDiscount()));
    }

    @ApiOperation("转换器(所有优惠匹配结果) - DiscountResult")
    public <R> List<R> convertForDiscountResult(Function<? super DiscountResult, ? extends R> mapper) {
        return getDiscountResultMap().values().stream().map(mapper).collect(Collectors.toList());
    }

    @ApiOperation("转换器(可用优惠匹配结果) - DiscountResult")
    public <R> List<R> convertForAvailableDiscountResult(Function<? super DiscountResult, ? extends R> mapper) {
        return getDiscountResultMap().values().stream()
            .filter(DiscountResult::getUsable)
            .map(mapper).collect(Collectors.toList());
    }

    @ApiOperation("转换器(不可用优惠匹配结果) - DiscountResult")
    public <R> List<R> convertForNoAvailableDiscountResult(Function<? super DiscountResult, ? extends R> mapper) {
        return getDiscountResultMap().values().stream()
            .filter(discountResult -> !discountResult.getUsable())
            .map(mapper).collect(Collectors.toList());
    }

    @ApiOperation("转换器(使用优惠匹配结果) - DiscountResult")
    public <R> List<R> convertForUsedDiscountResult(Function<? super DiscountResult, ? extends R> mapper) {
        return getDiscountResultMap().values().stream()
            .filter(discountResult -> discountResult.getDiscount().isUsed())
            .map(mapper).collect(Collectors.toList());
    }

    @ApiOperation("转换器(未使用优惠匹配结果) - DiscountResult")
    public <R> List<R> convertForNoUsedDiscountResult(Function<? super DiscountResult, ? extends R> mapper) {
        return getDiscountResultMap().values().stream()
            .filter(discountResult -> !discountResult.getDiscount().isUsed())
            .map(mapper).collect(Collectors.toList());
    }

    @ApiOperation("转换器(剩余可用优惠匹配结果) - DiscountResult")
    public <R> List<R> convertForSurplusAvailableDiscountResult(Function<? super DiscountResult, ? extends R> mapper) {
        return getDiscountResultMap().values().stream()
            .filter(DiscountResult::getUsable)
            .filter(discountResult -> !discountResult.getDiscount().isUsed())
            .map(mapper).collect(Collectors.toList());
    }

    protected void readjustment() {
        getUsedDiscount().forEach(discount -> {
            Serializable discountId = discount.getId();
            BigDecimal discountAmt = discount.getDiscountAmt();
            BigDecimal amortizedAmt = discountAmt;
            DiscountResult discountResult = this.getDiscountResult(discountId);

            List<OrderSupport.Item> items = discountResult.getConform().stream()
                .sorted(Comparator.comparing(o -> getRealAmtByOrderItem(o.getId())))
                .collect(Collectors.toList());
            BigDecimal totalRealAmount = getTotalRealAmount(items);
            int itemSize = items.size();

            Map<Serializable, BigDecimal> map = Maps.newHashMap();
            for (int i = 0; i < itemSize; i++) {
                OrderSupport.Item item = items.get(i);
                Serializable itemId = item.getId();
                BigDecimal forDiscountAmt;
                if (i != (itemSize - 1)) {
                    BigDecimal itemSaleAmt = getRealAmtByOrderItem(itemId);

                    // 分成比例
                    BigDecimal scale = MathUtils.divide(itemSaleAmt, totalRealAmount);

                    // 计算每个商品的折扣金额
                    forDiscountAmt = MathUtils.multiply(discountAmt, scale);
                } else {
                    forDiscountAmt = amortizedAmt;
                }
                map.put(itemId, forDiscountAmt);
                amortizedAmt = amortizedAmt.subtract(forDiscountAmt);
            }
            discountMaps.put(discountId, map);
        });
    }

    protected void initialization(OrderSupport order, List<? extends Discount<? extends OrderContext>> discounts) {
        this.order = order;
        this.discounts = discounts;
        discountMaps.clear();
        discountResultMap.clear();
    }

    protected void putDiscountResult(Serializable discountId, DiscountResult result) {
        discountResultMap.put(discountId, result);
    }

    @ApiOperation("选中订单明细优惠后金额总和")
    private BigDecimal getTotalRealAmount(List<OrderSupport.Item> items) {
        return items.stream().map(OrderSupport.Item::getId).map(this::getRealAmtByOrderItem)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }
}
