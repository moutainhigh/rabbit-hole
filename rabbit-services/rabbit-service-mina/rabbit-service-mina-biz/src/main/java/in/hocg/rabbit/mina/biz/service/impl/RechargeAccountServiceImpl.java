package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import in.hocg.rabbit.mina.biz.entity.RechargeAccount;
import in.hocg.rabbit.mina.biz.mapper.RechargeAccountMapper;
import in.hocg.rabbit.mina.biz.service.RechargeAccountService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * <p>
 * [功能模块] 充值账户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeAccountServiceImpl extends AbstractServiceImpl<RechargeAccountMapper, RechargeAccount> implements RechargeAccountService {


    @Override
    public Optional<RechargeAccount> getByOwnerUserId(Long userId) {
        Optional<RechargeAccount> rechargeAccountOpt = lambdaQuery().eq(RechargeAccount::getOwnerUserId, userId).oneOpt();
        if (rechargeAccountOpt.isEmpty()) {
            RechargeAccount entity = new RechargeAccount();
            entity.setOwnerUserId(userId);
            entity.setAvailAmt(BigDecimal.ZERO);
            entity.setApikey(IdUtil.fastSimpleUUID());
            saveOrUpdate(entity);
            rechargeAccountOpt = Optional.ofNullable(getById(entity.getId()));
        }
        return rechargeAccountOpt;
    }

    @Override
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 200))
    public boolean subtract(Long id, BigDecimal totalAmt) {
        BigDecimal oldAmt = getById(id).getAvailAmt();
        BigDecimal newAmt = oldAmt.subtract(totalAmt);
        Assert.isTrue(newAmt.compareTo(BigDecimal.ZERO) < 0, "余额不足");

        RechargeAccount update = new RechargeAccount();
        update.setAvailAmt(newAmt);
        return lambdaUpdate().eq(RechargeAccount::getAvailAmt, oldAmt)
            .eq(RechargeAccount::getId, id).update(update);
    }

    @Override
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 200))
    public boolean add(Long id, BigDecimal totalAmt) {
        BigDecimal oldAmt = getById(id).getAvailAmt();
        BigDecimal newAmt = oldAmt.add(totalAmt);
        RechargeAccount update = new RechargeAccount();
        update.setAvailAmt(newAmt);
        return lambdaUpdate().eq(RechargeAccount::getAvailAmt, oldAmt)
            .eq(RechargeAccount::getId, id).update(update);
    }

}
