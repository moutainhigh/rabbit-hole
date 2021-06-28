package com.github.lotus.com.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.UserIntegral;
import com.github.lotus.com.biz.entity.UserIntegralFlow;
import com.github.lotus.com.biz.mapper.UserIntegralMapper;
import com.github.lotus.com.biz.mapstruct.UserIntegralMapping;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralStatsVo;
import com.github.lotus.com.biz.service.UserIntegralFlowService;
import com.github.lotus.com.biz.service.UserIntegralService;
import com.github.lotus.common.datadict.com.integralflow.ChangeType;
import com.github.lotus.common.datadict.com.integralflow.EventType;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [通用] 用户积分表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserIntegralServiceImpl extends AbstractServiceImpl<UserIntegralMapper, UserIntegral> implements UserIntegralService {
    private final UserIntegralMapping mapping;
    private final UserIntegralFlowService userIntegralFlowService;

    @Override
    public MinaIntegralStatsVo getStats(Long userId) {
        LocalDate now = LocalDate.now();
        return getOrCreate(userId).map(userIntegral -> mapping.asMinaIntegralStatsVo(userIntegral)
            .setHasWatchAdUpperLimit(this.hasWatchAdUpperLimit(userId, now))
            .setHasSigned(this.exitUserSign(userId, now))).orElse(null);
    }

    @Override
    public IPage<MinaIntegralFlowVo> pageFlow(MinaIntegralFlowPageRo ro) {
        return userIntegralFlowService.pageFlow(ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void triggerUserSign(Long userId, LocalDateTime now) {
        BigDecimal plusValue = BigDecimal.ONE;
        // 1 年后过期
        LocalDateTime expireAt = now.plusYears(1);
        UserIntegralFlow entity = new UserIntegralFlow();
        entity.setEventType(EventType.UserSign.getCodeStr());
        entity.setChangeValue(plusValue);
        entity.setChangeType(ChangeType.Plus.getCodeStr());
        entity.setUserId(userId);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        entity.setExpireAt(expireAt);
        userIntegralFlowService.validInsert(entity);
        Assert.isTrue(this.casPlusAvlIntegral(userId, plusValue), "签到失败");
    }

    @Override
    public void triggerWatchAd(Long userId, LocalDateTime now) {
        BigDecimal plusValue = BigDecimal.ONE;
        // 1 年后过期
        LocalDateTime expireAt = now.plusYears(1);
        UserIntegralFlow entity = new UserIntegralFlow();
        entity.setEventType(EventType.WatchAd.getCodeStr());
        entity.setChangeValue(plusValue);
        entity.setChangeType(ChangeType.Plus.getCodeStr());
        entity.setUserId(userId);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        entity.setExpireAt(expireAt);
        userIntegralFlowService.validInsert(entity);
        Assert.isTrue(this.casPlusAvlIntegral(userId, plusValue), "观看视频失败");
    }

    @Override
    public Boolean exitUserSign(Long userId, LocalDate now) {
        return userIntegralFlowService.countSignByDate(userId, now) > 0;
    }

    @Override
    public Boolean hasWatchAdUpperLimit(Long userId, LocalDate now) {
        // 每日观看上限
        int maxCount = 10;
        return userIntegralFlowService.countWatchAdByDate(userId, now) > maxCount;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    protected Boolean plusAvailIntegral(Long userId, BigDecimal plusVal) {
        Optional<UserIntegral> userIntegralOpt = this.getOrCreate(userId);
        if (userIntegralOpt.isPresent()) {
            UserIntegral userIntegral = userIntegralOpt.get();
            BigDecimal avlIntegral = userIntegral.getAvailIntegral();
            return lambdaUpdate().eq(UserIntegral::getUserId, userId).eq(UserIntegral::getAvailIntegral, avlIntegral)
                .set(UserIntegral::getAvailIntegral, avlIntegral.add(plusVal)).update();
        }
        return false;
    }

    protected Boolean casPlusAvlIntegral(Long userId, BigDecimal plusVal) {
        int tryCatchCount = 10;
        for (int i = 0; i < tryCatchCount; i++) {
            if (this.plusAvailIntegral(userId, plusVal)) {
                return true;
            }
        }
        return false;
    }

    private Optional<UserIntegral> getOrCreate(Long userId) {
        Optional<UserIntegral> userIntegralOpt = lambdaQuery().eq(UserIntegral::getUserId, userId).oneOpt();
        if (!userIntegralOpt.isPresent()) {
            UserIntegral entity = new UserIntegral();
            entity.setAvailIntegral(BigDecimal.ZERO);
            entity.setUsedIntegral(BigDecimal.ZERO);
            entity.setUserId(userId);
            entity.setCreator(userId);
            entity.setCreatedAt(LocalDateTime.now());
            this.validInsert(entity);
            return Optional.of(entity);
        }
        return userIntegralOpt;
    }
}
