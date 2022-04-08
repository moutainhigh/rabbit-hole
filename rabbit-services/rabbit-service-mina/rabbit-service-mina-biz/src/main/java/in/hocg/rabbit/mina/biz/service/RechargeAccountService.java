package in.hocg.rabbit.mina.biz.service;

import in.hocg.rabbit.mina.biz.entity.RechargeAccount;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * <p>
 * [功能模块] 充值账户表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
public interface RechargeAccountService extends AbstractService<RechargeAccount> {

    Optional<RechargeAccount> getByOwnerUserId(Long userId);

    /**
     * 扣款
     * @param id
     * @param totalAmt
     * @return
     */
    boolean subtract(Long id, BigDecimal totalAmt);

    /**
     * 增加
     * @param id
     * @param totalAmt
     * @return
     */
    boolean add(Long id, BigDecimal totalAmt);
}
