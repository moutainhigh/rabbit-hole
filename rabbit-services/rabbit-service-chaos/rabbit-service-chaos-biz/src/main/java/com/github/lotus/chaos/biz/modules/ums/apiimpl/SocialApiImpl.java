package com.github.lotus.chaos.biz.modules.ums.apiimpl;

import com.github.lotus.chaos.api.modules.ums.SocialApi;
import com.github.lotus.chaos.api.modules.ums.pojo.ro.InsertSocialRo;
import com.github.lotus.chaos.api.modules.ums.pojo.vo.UserDetailVo;
import com.github.lotus.chaos.biz.modules.ums.service.SocialService;
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
