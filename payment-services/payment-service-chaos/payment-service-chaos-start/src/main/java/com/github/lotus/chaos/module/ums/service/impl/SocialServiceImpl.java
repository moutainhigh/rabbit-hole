package com.github.lotus.chaos.module.ums.service.impl;

import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.module.ums.entity.Social;
import com.github.lotus.chaos.module.ums.mapper.SocialMapper;
import com.github.lotus.chaos.module.ums.mapstruct.SocialMapping;
import com.github.lotus.chaos.module.ums.service.AccountService;
import com.github.lotus.chaos.module.ums.service.SocialService;
import com.github.lotus.chaos.modules.ums.api.ro.InsertSocialRo;
import com.github.lotus.chaos.modules.ums.api.vo.UserDetailVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [用户模块] 绑定社交账号表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SocialServiceImpl extends AbstractServiceImpl<SocialMapper, Social> implements SocialService {
    private final AccountService accountService;
    private final SocialMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVo getUserBySocialTypeAndSocialId(String socialType, String socialId) {
        Optional<Account> accountOpt = getAccountBySocialTypeAndSocialId(socialType, socialId);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            return accountService.getUser(account.getUsername());
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(InsertSocialRo ro) {
        Social entity = mapping.asSocial(ro);
        entity.setCreatedAt(LocalDateTime.now());
        validInsertOrUpdate(entity);
    }

    private Optional<Account> getAccountBySocialTypeAndSocialId(String socialType, String socialId) {
        return baseMapper.getAccountBySocialTypeAndSocialId(socialType, socialId);
    }
}
