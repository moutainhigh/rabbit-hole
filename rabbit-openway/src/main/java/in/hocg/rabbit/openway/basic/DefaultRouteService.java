package in.hocg.rabbit.openway.basic;

import in.hocg.rabbit.openway.basic.route.RouteService;
import in.hocg.rabbit.openway.basic.data.AppInfo;
import in.hocg.rabbit.openway.basic.data.ServiceRoute;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DefaultRouteService implements RouteService {
    @Override
    public List<ServiceRoute> listAll() {
        ServiceRoute route1 = new ServiceRoute();
        route1.setApiId("E12453");
        route1.setMapTargetUri("lb://rabbit-chaos");
        route1.setMapTargetPath("/wallpaper/_complete");

        ServiceRoute route2 = new ServiceRoute();
        route2.setApiId("E12452");
        route2.setMapTargetUri("http://127.0.0.1:21000");
        route2.setMapTargetPath("/wallpaper/_complete");

        return List.of(route1, route2);
    }

    @Override
    public boolean hasAuthority(String appid, String method) {
        return true;
    }

    @Override
    public Optional<AppInfo> getAppid(String appid) {
        return Optional.ofNullable(new AppInfo().setUsername("hocgin"));
    }
}
