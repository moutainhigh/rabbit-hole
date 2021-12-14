package in.hocg.rabbit.chaos.api.named;

import in.hocg.rabbit.chaos.api.ChaosServiceName;
import in.hocg.boot.named.annotation.NamedService;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.named.ifc.NamedHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ChaosServiceName.NAME)
public interface ChaosNamedServiceApi extends NamedService {
    String CONTEXT_ID = "ChaosNamedAPI";

    @NamedHandler(ChaosNamedType.DataDictName)
    @PostMapping(value = CONTEXT_ID + "/loadByDataDict", headers = ChaosServiceName.FEIGN_HEADER)
    Map<String, Object> loadByDataDict(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedType.UserId2Username)
    @PostMapping(value = CONTEXT_ID + "/loadByUserName", headers = ChaosServiceName.FEIGN_HEADER)
    Map<String, Object> loadByUserName(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedType.Userid2Nickname)
    @PostMapping(value = CONTEXT_ID + "/loadByNickname", headers = ChaosServiceName.FEIGN_HEADER)
    Map<String, Object> loadByNickname(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedType.ProjectName)
    @PostMapping(value = CONTEXT_ID + "/loadByProjectName", headers = ChaosServiceName.FEIGN_HEADER)
    Map<String, Object> loadByProjectName(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedType.DistrictName)
    @PostMapping(value = CONTEXT_ID + "/loadByDistrictName", headers = ChaosServiceName.FEIGN_HEADER)
    Map<String, Object> loadByDistrictName(@RequestBody NamedArgs args);
}
