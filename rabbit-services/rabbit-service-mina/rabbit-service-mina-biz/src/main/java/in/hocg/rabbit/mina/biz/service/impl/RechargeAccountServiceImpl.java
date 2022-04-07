package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.common.utils.MathUtils;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
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
import java.util.List;
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
        return lambdaQuery().eq(RechargeAccount::getOwnerUserId, userId).oneOpt();
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
