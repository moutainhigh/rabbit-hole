package in.hocg.rabbit.gateway.service;

import in.hocg.rabbit.ums.api.pojo.vo.UserDetailVo;

import java.util.List;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface UserService {
    boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri);

    UserDetailVo getUserDetail(String username);

    List<String> getAuthorities(String username);

    String getUsername(String token);
}
