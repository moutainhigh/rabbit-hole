package in.hocg.rabbit.owp.biz.apiimpl;

import in.hocg.rabbit.owp.api.OwpServiceApi;
import in.hocg.rabbit.owp.api.pojo.vo.ApiRouterVo;
import in.hocg.rabbit.owp.api.pojo.vo.DevAppVo;
import in.hocg.rabbit.owp.biz.service.OwpApiService;
import in.hocg.rabbit.owp.biz.service.DeveloperAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OwpServiceApiImpl implements OwpServiceApi {
    private final OwpApiService apiService;
    private final DeveloperAppService developerAppService;

    @Override
    public List<ApiRouterVo> listAll() {
        return apiService.listAll();
    }

    @Override
    public boolean hasAuthority(String appid, String method) {
        return apiService.hasAuthority(appid, method);
    }

    @Override
    public DevAppVo getByEncoding(String appid) {
        return developerAppService.getByEncoding(appid);
    }
}
