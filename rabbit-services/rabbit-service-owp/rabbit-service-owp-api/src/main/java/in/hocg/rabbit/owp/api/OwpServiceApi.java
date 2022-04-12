package in.hocg.rabbit.owp.api;

import in.hocg.rabbit.owp.api.pojo.vo.ApiRouterVo;
import in.hocg.rabbit.owp.api.pojo.vo.DevAppVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = OwpServiceName.NAME)
public interface OwpServiceApi {
    String CONTEXT_ID = "OwpServiceApi";

    @PostMapping(value = CONTEXT_ID + "/listAll", headers = OwpServiceName.FEIGN_HEADER)
    List<ApiRouterVo> listAll();

    @PostMapping(value = CONTEXT_ID + "/hasAuthority", headers = OwpServiceName.FEIGN_HEADER)
    boolean hasAuthority(@RequestParam("appid") String appid, @RequestParam("method") String method);

    @PostMapping(value = CONTEXT_ID + "/getByEncoding", headers = OwpServiceName.FEIGN_HEADER)
    DevAppVo getByEncoding(@RequestParam("appid") String appid);
}
