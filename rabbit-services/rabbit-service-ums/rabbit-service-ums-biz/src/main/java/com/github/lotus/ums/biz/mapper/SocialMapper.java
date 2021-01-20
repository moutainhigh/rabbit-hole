package com.github.lotus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.ums.biz.entity.User;
import com.github.lotus.ums.biz.entity.Social;
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

    Optional<User> getAccountBySocialTypeAndSocialId(@Param("socialType") String socialType, @Param("socialId") String socialId);
}
