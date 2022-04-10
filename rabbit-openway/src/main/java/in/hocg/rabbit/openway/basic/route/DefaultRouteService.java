package in.hocg.rabbit.openway.basic.route;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DefaultRouteService implements RouteService {
    @Override
    public List<ServiceRoute> listAll() {
        return Collections.emptyList();
    }

    @Override
    public boolean hasAuthority(String appid, String method) {
        return true;
    }
}
