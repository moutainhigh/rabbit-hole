package in.hocg.rabbit.owp.biz.convert;

import in.hocg.rabbit.owp.api.pojo.vo.DevAppVo;
import in.hocg.rabbit.owp.biz.entity.DeveloperApp;
import in.hocg.rabbit.owp.biz.mapstruct.DeveloperAppMapping;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.named.UmsNamedServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OwpConvert {
    private final DeveloperAppMapping developerAppMapping;
    private final UserServiceApi userServiceApi;

    public DevAppVo asDevAppVo(DeveloperApp entity) {
        AccountVo account = userServiceApi.getById(entity.getId());
        return developerAppMapping.asDevAppVo(entity)
            .setSecretKey(entity.getSecretKey())
            .setUsername(account.getUsername());
    }
}
