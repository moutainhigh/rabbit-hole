package com.github.lotus.chaos.api;

import com.github.lotus.common.constant.GlobalConstant;
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
public interface ChaosNamedApi extends NamedService {
    String CONTEXT_ID = "ChaosNamedAPI";

    @NamedHandler(NamedType.DataDict)
    @PostMapping(value = CONTEXT_ID + "/loadByDataDict", headers = GlobalConstant.FEIGN_HEADER)
    Map<String, Object> loadByDataDict(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.UserId2Username)
    @PostMapping(value = CONTEXT_ID + "/loadByUserName", headers = GlobalConstant.FEIGN_HEADER)
    Map<String, Object> loadByUserName(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.Userid2Nickname)
    @PostMapping(value = CONTEXT_ID + "/loadByNickname", headers = GlobalConstant.FEIGN_HEADER)
    Map<String, Object> loadByNickname(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.ProjectName)
    @PostMapping(value = CONTEXT_ID + "/loadByProjectName", headers = GlobalConstant.FEIGN_HEADER)
    Map<String, Object> loadByProjectName(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.DistrictName)
    @PostMapping(value = CONTEXT_ID + "/loadByDistrictName", headers = GlobalConstant.FEIGN_HEADER)
    Map<String, Object> loadByDistrictName(@RequestBody NamedArgs args);

    @NamedHandler(NamedType.AccessMchName)
    @PostMapping(value = CONTEXT_ID + "/loadByAccessMchName", headers = GlobalConstant.FEIGN_HEADER)
    Map<String, Object> loadByAccessMchName(@RequestBody NamedArgs args);
}
