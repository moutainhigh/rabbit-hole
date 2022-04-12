package in.hocg.rabbit.openway.basic.route;

import in.hocg.rabbit.openway.basic.data.AppInfo;
import in.hocg.rabbit.openway.basic.data.ServiceRoute;

import java.util.List;
import java.util.Optional;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RouteService {

    List<ServiceRoute> listAll();

    boolean hasAuthority(String appid, String method);

    Optional<AppInfo> getAppid(String appid);


}
