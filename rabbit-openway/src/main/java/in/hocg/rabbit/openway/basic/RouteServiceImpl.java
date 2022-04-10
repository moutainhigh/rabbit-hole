package in.hocg.rabbit.openway.basic;

import in.hocg.rabbit.openway.basic.route.RouteService;
import in.hocg.rabbit.openway.basic.data.AppInfo;
import in.hocg.rabbit.openway.basic.data.ServiceRoute;
import in.hocg.rabbit.owp.api.OwpServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RouteServiceImpl implements RouteService {
    private final OwpServiceApi owpServiceApi;

    @Override
    public List<ServiceRoute> listAll() {
        return owpServiceApi.listAll().parallelStream()
            .map(apiRouter -> new ServiceRoute()
                .setApiId(apiRouter.getEncoding())
                .setMapTargetPath(apiRouter.getMapPath())
                .setMapTargetUri(apiRouter.getMapUri()))
            .collect(Collectors.toList());
    }

    @Override
    public boolean hasAuthority(String appid, String method) {
        return owpServiceApi.hasAuthority(appid, method);
    }

    @Override
    public Optional<AppInfo> getAppid(String appid) {
        return Optional.ofNullable(owpServiceApi.getByEncoding(appid))
            .map(devAppVo -> new AppInfo()
                .setUsername(devAppVo.getUsername())
                .setSecretKey(devAppVo.getSecretKey())
                .setExpired(devAppVo.getExpired()));
    }
}
