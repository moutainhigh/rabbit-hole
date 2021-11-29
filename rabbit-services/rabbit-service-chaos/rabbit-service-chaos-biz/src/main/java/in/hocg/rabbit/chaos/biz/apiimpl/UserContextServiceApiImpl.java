package in.hocg.rabbit.chaos.biz.apiimpl;

import in.hocg.rabbit.chaos.api.UserContextServiceApi;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;
import in.hocg.rabbit.usercontext.ifc.vo.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
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
