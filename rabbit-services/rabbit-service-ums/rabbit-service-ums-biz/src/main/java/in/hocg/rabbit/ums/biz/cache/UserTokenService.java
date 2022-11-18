package in.hocg.rabbit.ums.biz.cache;

/**
 * Created by hocgin on 2020/7/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface UserTokenService {

    String getUsername(String token);

    String renewUserToken(String token);

    String getUserToken(String username);

    void removeUserToken(String token);
}
