package com.github.lotus.chaos.biz.apiimpl;

import com.github.lotus.chaos.api.UserContextServiceApi;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.usercontext.ifc.UserContextService;
import com.github.lotus.usercontext.ifc.vo.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by hocgin on 2020/11/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserContextServiceApiImpl implements UserContextServiceApi {
    private final UserServiceApi accountServiceApi;

    @Override
    public UserDetail getUserDetail(String username) {
        UserDetailVo userDetail = accountServiceApi.getUserDetailVoByUsername(username);
        if (Objects.isNull(userDetail)) {
            return null;
        }
        return new UserDetail().setUsername(userDetail.getUsername())
            .setId(userDetail.getId());
    }
}
