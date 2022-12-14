package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.com.api.UniqueCodeServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.common.utils.MathUtils;
import in.hocg.rabbit.mina.biz.pojo.ro.QueryRechargeOrderRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.cache.RechargeCacheService;
import in.hocg.rabbit.mina.biz.convert.RechargeOrderConvert;
import in.hocg.rabbit.mina.biz.docking.recharge.RechargeDockingService;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.CheckRechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.QueryProductRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.RechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.CheckRechargeVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ProductVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.RechargeVo;
import in.hocg.rabbit.mina.biz.entity.RechargeAccount;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import in.hocg.rabbit.mina.api.enums.RechargeOrderStatus;
import in.hocg.rabbit.mina.biz.mapper.RechargeOrderMapper;
import in.hocg.rabbit.mina.biz.mapstruct.RechargeMapping;
import in.hocg.rabbit.mina.biz.pojo.dto.Notify;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeOrderPageRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeProductCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeAccountVo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderOrdinaryVo;
import in.hocg.rabbit.mina.biz.schedule.RechargeNotifySchedule;
import in.hocg.rabbit.mina.biz.service.RechargeAccountService;
import in.hocg.rabbit.mina.biz.service.RechargeOrderService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * [????????????] ??????????????? ???????????????
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
@Slf4j
@Service
@UseConvert(RechargeOrderConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeOrderServiceImpl extends AbstractServiceImpl<RechargeOrderMapper, RechargeOrder> implements RechargeOrderService {
    private final RechargeMapping mapping;
    private final RechargeDockingService rechargeDockingService;
    private final UniqueCodeServiceApi uniqueCodeServiceApi;
    private final RechargeAccountService rechargeAccountService;
    private final RechargeCacheService rechargeCacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RechargeOrderVo recharge(RechargeOrderRo ro) {
        Long userId = Assert.notNull(ro.getUserId(), "??????ID????????????");
        String productId = ro.getProductId();
        BigDecimal maxCostAmt = ro.getMaxCostAmt();

        RechargeAccount account = rechargeAccountService.getByOwnerUserId(userId)
            .orElseThrow(() -> ServiceException.wrap("?????????????????????"));

        String uniqueCode = uniqueCodeServiceApi.getUniqueCode(CodeType.MaintainOrder.getCodeStr());

        // ????????????
        Map<String, RechargeProductVo> productMaps = LangUtils.toMap(rechargeCacheService.listProduct(userId), RechargeProductVo::getId);
        RechargeProductVo vo = Assert.notNull(productMaps.get(productId), "?????????????????????");
        BigDecimal productAmt = vo.getPrice();
        Assert.isTrue(Objects.isNull(maxCostAmt) || maxCostAmt.compareTo(productAmt) <= 0, "??????????????????????????????");

        // 1. ??????????????????
        RechargeOrder entity = mapping.asRechargeOrder(ro);
        entity.setProductName(RechargeHelper.getTitle(vo));
        entity.setOrderNo(uniqueCode);
        entity.setTotalAmt(productAmt);
        validInsertOrUpdate(entity);

        // 2. ??????
        Assert.isTrue(rechargeAccountService.subtract(account.getId(), entity.getTotalAmt()), "????????????");

        // 3. ??????
        String orderNo = entity.getOrderNo();
        RechargeVo result = rechargeDockingService.recharge(new RechargeRo()
            .setNotifyUrl(RechargeHelper.getNotifyUrl(orderNo))
            .setProductId(entity.getProductId())
            .setMobile(entity.getAccount())
            .setOutTradeNum(orderNo)
            .setPrice(LangUtils.callIfNotNull(maxCostAmt, BigDecimal::intValue).orElse(productAmt.intValue())));
        // todo ????????????????????????

        return as(getById(entity.getId()), RechargeOrderVo.class);
    }

    @Override
    public List<RechargeProductVo> listProduct(Long userId) {
        RechargeAccount account = rechargeAccountService.getByOwnerUserId(userId)
            .orElseThrow(() -> ServiceException.wrap("?????????????????????"));
        return listProduct(account);
    }

    public List<RechargeProductVo> listProduct(RechargeAccount account) {
        QueryProductRo ro = new QueryProductRo();
        BigDecimal freeRate = account.getFreeRate();

        List<ProductVo> queryResult = rechargeDockingService.listProduct(ro);
        return queryResult.parallelStream()
            .flatMap(productVo -> CollUtil.emptyIfNull(productVo.getProducts()).stream())
            .map(product -> {
                BigDecimal price = Objects.isNull(freeRate) ? product.getYPrice()
                    : MathUtils.multiply(BigDecimal.ONE.add(freeRate), product.getPrice());
                return new RechargeProductVo()
                    .setPrice(product.getYPrice())
                    .setProductName(product.getName())
                    .setClassName(product.getTypeName())
                    .setPrice(price)
                    .setDesc(product.getDesc())
                    .setId(product.getId());
            })
            .collect(Collectors.toList());
    }

    @Override
    public RechargeOrderVo queryRecharge(QueryRechargeOrderRo ro) {
        RechargeOrder entity = lambdaQuery().eq(CommonEntity::getCreator, ro.getUserId())
            .eq(RechargeOrder::getOutOrderNo, ro.getOutOrderNo()).one();
        return as(entity, RechargeOrderVo.class);
    }

    @Override
    public List<RechargeOrder> listByExecutingAndCreatedAt(LocalDateTime startAt, LocalDateTime endAt) {
        return lambdaQuery().ge(CommonEntity::getCreatedAt, startAt)
            .le(CommonEntity::getCreatedAt, endAt).eq(RechargeOrder::getStatus, RechargeOrderStatus.Executing.getCodeStr()).list();
    }

    @Override
    public List<RechargeOrder> listByWaitNotify(LocalDateTime startAt, LocalDateTime endAt) {
        return lambdaQuery().in(RechargeOrder::getStatus, List.of(RechargeOrderStatus.Success.getCodeStr(), RechargeOrderStatus.Failed.getCodeStr()))
            .isNull(RechargeOrder::getFinishNotifyAt)
            .le(RechargeOrder::getNextNotifyAt, LocalDateTime.now())
            .list();
    }

    private void success(Long id) {
        RechargeOrder update = new RechargeOrder().setStatus(RechargeOrderStatus.Success.getCodeStr());
        boolean isOk = lambdaUpdate().eq(RechargeOrder::getId, id).update(update);
        Assert.isTrue(isOk, "????????????");
    }

    protected void fail(Long id, String failMsg) {
        RechargeOrder order = getById(id);
        RechargeOrder update = new RechargeOrder().setStatus(RechargeOrderStatus.Failed.getCodeStr()).setFailReason(failMsg);
        boolean isOk = lambdaUpdate().eq(RechargeOrder::getId, id).update(update);
        Assert.isTrue(isOk, "????????????");
        Long creatorId = update.getCreator();

        // ????????????
        rechargeAccountService.add(creatorId, order.getTotalAmt());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rechargeNotify(String orderNo) {
        RechargeOrder order = lambdaQuery().eq(RechargeOrder::getOrderNo, orderNo).one();
        if (Objects.isNull(order)) {
            return;
        }
        tryHandleRechargeResult(order);
    }

    @Override
    public void notifyAccess(Long id, Long userId) {
        RechargeOrder order = lambdaQuery().eq(CommonEntity::getId, id).eq(CommonEntity::getCreator, userId).one();
        notifyAccess(Assert.notNull(order, "???????????????"));
    }

    @Override
    public void notifyAccess(RechargeOrder entity) {
        Integer count = ObjectUtil.defaultIfNull(entity.getNotifyRecount(), 0);
        String notifyUrl = entity.getNotifyUrl();
        Long orderId = entity.getId();
        if (Objects.isNull(notifyUrl)) {
            this.notifyResult(orderId, false, count, "notifyUrl is null", null);
            return;
        }
        count += 1;

        // 3. ????????????
        String returnBody;
        try {
            RechargeOrderVo body = this.as(entity, RechargeOrderVo.class);
            returnBody = HttpUtil.post(notifyUrl, JSON.toJSONString(body), 5 * 1000);
        } catch (Exception e) {
            log.warn("??????????????????????????????, ??????????????????[ID={}], ????????????: {}", orderId, e);
            returnBody = StrUtil.format("??????????????????????????????: {}", e.getMessage());
        }

        // 4. ????????????
        boolean isSuccess = "notify_success".equalsIgnoreCase(StrUtil.nullToEmpty(returnBody).trim());
        if (isSuccess) {
            this.notifyResult(orderId, true, count, returnBody, null);
            return;
        }

        // 5. ?????????????????????????????????
        if (count >= RechargeNotifySchedule.MAX_COUNT) {
            this.notifyResult(orderId, false, count, "?????????????????????????????????", null);
        }
        // 5. ????????????????????????????????????
        else {
            this.notifyResult(orderId, null, count, returnBody, RechargeNotifySchedule.getNextNotifyTime(entity.getNextNotifyAt(), count));
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tryHandleRechargeResult(RechargeOrder entity) {
        try {
            CheckRechargeRo ro = new CheckRechargeRo();
            ro.setOutTradeNums(entity.getOrderNo());
            List<CheckRechargeVo> list = rechargeDockingService.checkRecharge(ro);
            CheckRechargeVo checkResult = list.get(0);
            if (checkResult.isSuccess()) {
                this.success(entity.getId());
            } else if (checkResult.isFail()) {
                this.fail(entity.getId(), "????????????");
            }
        } catch (Exception e) {
            log.error("[RechargeNotifySchedule] handleQuery error, entity: {}", entity, e);
        }
    }

    @Override
    public void notifyResult(Long id, Boolean isOk, Integer nowCount, String notifyResult, LocalDateTime nextNotifyAt) {
        RechargeOrder update = new RechargeOrder();
        update.setId(id);
        update.setNotifyRecount(nowCount);
        update.setLastNotifyResult(notifyResult);
        update.setNextNotifyAt(nextNotifyAt);
        update.setFinishNotifyAt(isOk ? LocalDateTime.now() : null);
        updateById(update);
    }

    @Override
    public IPage<RechargeOrderOrdinaryVo> paging(RechargeOrderPageRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(entity -> as(entity, RechargeOrderOrdinaryVo.class));
    }

    @Override
    public RechargeAccountVo getAccount(Long userId) {
        Optional<RechargeAccount> accountOpt = rechargeAccountService.getByOwnerUserId(userId);
        if (accountOpt.isEmpty()) {
            return new RechargeAccountVo();
        }
        return mapping.asRechargeAccount(accountOpt.get());
    }

    @Override
    public List<KeyValue> completeWithProduct(RechargeProductCompleteRo ro) {
        return rechargeCacheService.listProduct(ro.getOpsUserId()).stream()
            .map(item -> new KeyValue().setKey(RechargeHelper.getTitle(item)).setValue(item.getId()))
            .filter(keyValue -> {
                String keyword = ro.getKeyword();
                return StrUtil.isBlank(keyword) || StrUtil.containsIgnoreCase(StrUtil.toString(keyValue.getKey()), keyword);
            })
            .collect(Collectors.toList());
    }

}
