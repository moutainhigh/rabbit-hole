package com.github.lotus.chaos.biz.apiimpl;

import com.github.lotus.ums.api.AccountServiceApi;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import com.github.lotus.usercontext.ifc.UserContextService;
import com.github.lotus.usercontext.ifc.vo.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by hocgin on 2020/11/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserContextServiceImpl implements UserContextService {
    private final AccountServiceApi accountServiceApi;

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
