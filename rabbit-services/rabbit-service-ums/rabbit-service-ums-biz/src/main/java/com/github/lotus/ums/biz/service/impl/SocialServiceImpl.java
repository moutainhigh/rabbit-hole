package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.api.pojo.ro.InsertSocialRo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.Social;
import com.github.lotus.ums.biz.mapper.SocialMapper;
import com.github.lotus.ums.biz.mapstruct.SocialMapping;
import com.github.lotus.ums.biz.service.UserService;
import com.github.lotus.ums.biz.service.SocialService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
public class SocialServiceImpl extends AbstractServiceImpl<SocialMapper, Social>
    implements SocialService {
    private final UserService accountService;
    private final SocialMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<UserDetailVo> getUserBySocialTypeAndSocialId(String socialType, String socialId) {
        Optional<User> accountOpt = getAccountBySocialTypeAndSocialId(socialType, socialId);
        return accountOpt.map(User::getUsername)
            .map(accountService::getUserDetailVoByUsername);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(InsertSocialRo ro) {
        Social entity = mapping.asSocial(ro);
        entity.setCreatedAt(LocalDateTime.now());
        validInsertOrUpdate(entity);
    }

    @Override
    public List<Social> listSocialByAccountId(Long accountId) {
        return lambdaQuery().eq(Social::getAccountId, accountId)
            .list();
    }

    private Optional<User> getAccountBySocialTypeAndSocialId(String socialType, String socialId) {
        return baseMapper.getAccountBySocialTypeAndSocialId(socialType, socialId);
    }
}
