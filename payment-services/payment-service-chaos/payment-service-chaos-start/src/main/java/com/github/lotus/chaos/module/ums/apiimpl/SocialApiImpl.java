package com.github.lotus.chaos.module.ums.apiimpl;

import com.github.lotus.chaos.module.ums.service.SocialService;
import com.github.lotus.chaos.modules.ums.api.SocialApi;
import com.github.lotus.chaos.modules.ums.ro.InsertSocialRo;
import com.github.lotus.chaos.modules.ums.vo.UserDetailVo;
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
