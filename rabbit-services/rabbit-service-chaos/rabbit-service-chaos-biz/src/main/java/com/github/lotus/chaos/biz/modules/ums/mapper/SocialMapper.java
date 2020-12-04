package com.github.lotus.chaos.biz.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.chaos.biz.modules.ums.entity.Account;
import com.github.lotus.chaos.biz.modules.ums.entity.Social;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 绑定社交账号表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-30
 */
@Mapper
public interface SocialMapper extends BaseMapper<Social> {

    Optional<Account> getAccountBySocialTypeAndSocialId(@Param("socialType") String socialType, @Param("socialId") String socialId);
}
