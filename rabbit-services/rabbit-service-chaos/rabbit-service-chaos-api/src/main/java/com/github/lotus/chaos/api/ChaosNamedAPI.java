package com.github.lotus.chaos.api;

import in.hocg.boot.named.autoconfiguration.annotation.NamedService;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.named.autoconfiguration.ifc.NamedHandler;
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
@FeignClient(value = ServiceName.NAME, contextId = ChaosNamedAPI.CONTEXT_ID)
public interface ChaosNamedAPI extends NamedService {
    String CONTEXT_ID = "ChaosNamedAPI";

    String DATA_DICT = "DATA_DICT";
    String USERID2USERNAME = "USERID2USERNAME";
    String USERID2NICKNAME = "USERID2NICKNAME";
    String COM_DistrictName = "COM_DistrictName";

    @NamedHandler(ChaosNamedAPI.DATA_DICT)
    @PostMapping(CONTEXT_ID + "/loadByDataDict")
    Map<String, Object> loadByDataDict(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedAPI.USERID2USERNAME)
    @PostMapping(CONTEXT_ID + "/loadByUserName")
    Map<String, Object> loadByUserName(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedAPI.USERID2NICKNAME)
    @PostMapping(CONTEXT_ID + "/loadByNickname")
    Map<String, Object> loadByNickname(@RequestBody NamedArgs args);

    @NamedHandler(ChaosNamedAPI.COM_DistrictName)
    @PostMapping(CONTEXT_ID + "/loadByDistrictName")
    Map<String, Object> loadByDistrictName(@RequestBody NamedArgs args);
}
