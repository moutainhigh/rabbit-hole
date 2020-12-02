package com.github.lotus.chaos.module.ums.service;

import com.github.lotus.chaos.module.ums.entity.Social;
import com.github.lotus.chaos.modules.ums.api.ro.InsertSocialRo;
import com.github.lotus.chaos.modules.ums.api.vo.UserDetailVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [用户模块] 绑定社交账号表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-30
 */
public interface SocialService extends AbstractService<Social> {

    UserDetailVo getUserBySocialTypeAndSocialId(String socialType, String socialId);

    void insertOne(InsertSocialRo ro);
}
