package com.github.lotus.chaos.module.support.apiimpl;

import com.github.lotus.chaos.module.ums.service.AccountService;
import com.github.lotus.chaos.modules.ums.vo.UserDetailVo;
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
    private final AccountService accountService;

    @Override
    public UserDetail getUserDetail(String username) {
        UserDetailVo userDetail = accountService.getUser(username);
        if (Objects.isNull(userDetail)) {
            return null;
        }
        return new UserDetail()
            .setUsername(userDetail.getUsername())
            .setId(userDetail.getId());
    }
}
