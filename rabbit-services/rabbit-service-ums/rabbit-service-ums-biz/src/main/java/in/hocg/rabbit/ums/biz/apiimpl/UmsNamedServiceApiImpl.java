package in.hocg.rabbit.ums.biz.apiimpl;

import in.hocg.boot.named.autoconfiguration.core.AbsNamedServiceExpand;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.rabbit.ums.api.named.UmsNamedServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UmsNamedServiceApiImpl extends AbsNamedServiceExpand
    implements UmsNamedServiceApi {
    private final UserService userService;

    @Override
    public Map<String, Object> loadByUserId(NamedArgs args) {
        List<User> result = userService.listByIds(args.getValues());
        return this.toMap(result, User::getId, User::getUsername);
    }
}
