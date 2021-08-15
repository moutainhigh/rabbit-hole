package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.biz.entity.Account;
import com.github.lotus.bmw.biz.pojo.dto.CreateAccountDto;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付账户表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface AccountService extends AbstractService<Account> {

    Optional<Account> getAccount(Long userId, Long accessMchId, Long paymentMchId, String useScenes);

    Account createAccount(CreateAccountDto dto);
}
