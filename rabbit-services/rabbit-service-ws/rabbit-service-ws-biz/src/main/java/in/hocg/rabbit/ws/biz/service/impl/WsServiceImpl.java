package in.hocg.rabbit.ws.biz.service.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.rabbit.common.utils.JwtUtils;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.named.UmsNamedServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.rabbit.ws.biz.service.WsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by hocgin on 2022/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WsServiceImpl implements WsService {
    private final UserServiceApi userServiceApi;

    @Override
    public String getTicket(Long userId) {
        AccountVo user = Assert.notNull(userServiceApi.getById(userId), "用户不存在");
        return JwtUtils.encode(user.getUsername());
    }
}
