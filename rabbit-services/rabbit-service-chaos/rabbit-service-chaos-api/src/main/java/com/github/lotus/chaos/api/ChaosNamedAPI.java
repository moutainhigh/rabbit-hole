package com.github.lotus.chaos.api;

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
@FeignClient(value = ServiceName.NAME)
public interface ChaosNamedAPI extends NamedService {
    String CONTEXT_ID = "ChaosNamedAPI";

    @NamedHandler(NamedType.DataDict)
    @PostMapping(CONTEXT_ID + "/loadByDataDict")
    Map<String, Object> loadByDataDict(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.UserId2Username)
    @PostMapping(CONTEXT_ID + "/loadByUserName")
    Map<String, Object> loadByUserName(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.Userid2Nickname)
    @PostMapping(CONTEXT_ID + "/loadByNickname")
    Map<String, Object> loadByNickname(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.ProjectName)
    @PostMapping(CONTEXT_ID + "/loadByProjectName")
    Map<String, Object> loadByProjectName(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.DistrictName)
    @PostMapping(CONTEXT_ID + "/loadByDistrictName")
    Map<String, Object> loadByDistrictName(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.AccessAppName)
    @PostMapping(CONTEXT_ID + "/loadByAccessAppName")
    Map<String, Object> loadByAccessAppName(@RequestBody NamedArgs args);
}
