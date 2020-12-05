package com.github.lotus.chaos.biz.modules.ums.service;

import com.github.lotus.chaos.api.modules.ums.pojo.ro.InsertSocialRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
import com.github.lotus.chaos.biz.modules.ums.entity.Social;
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
