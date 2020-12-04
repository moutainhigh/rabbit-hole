package com.github.lotus.chaos.biz.module.ums.apiimpl;

import com.github.lotus.chaos.api.modules.ums.api.SocialApi;
import com.github.lotus.chaos.api.modules.ums.api.ro.InsertSocialRo;
import com.github.lotus.chaos.api.modules.ums.api.vo.UserDetailVo;
import com.github.lotus.chaos.biz.module.ums.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class SocialApiImpl implements SocialApi {
    private final SocialService service;

    @Override
    public UserDetailVo getAccountBySocialTypeAndSocialId(String socialType, String socialId) {
        return service.getUserBySocialTypeAndSocialId(socialType, socialId);
    }

    @Override
    public void insertOne(InsertSocialRo ro) {
        service.insertOne(ro);
    }
}