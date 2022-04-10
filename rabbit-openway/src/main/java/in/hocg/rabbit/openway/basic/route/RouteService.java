package in.hocg.rabbit.openway.basic.route;

import java.util.List;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RouteService {

    List<ServiceRoute> listAll();

    boolean hasAuthority(String appid, String method);
}
