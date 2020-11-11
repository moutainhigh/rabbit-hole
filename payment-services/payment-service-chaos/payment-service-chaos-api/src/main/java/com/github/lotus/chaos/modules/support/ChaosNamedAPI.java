package com.github.lotus.chaos.modules.support;

import in.hocg.boot.named.autoconfiguration.annotation.NamedService;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.named.autoconfiguration.ifc.NamedHandler;

import java.util.Map;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ChaosNamedAPI extends NamedService {
    String DATA_DICT = "DATA_DICT";
    String USERID2USERNAME = "USERID2USERNAME";
    String USERID2NICKNAME = "USERID2NICKNAME";

    @NamedHandler(ChaosNamedAPI.DATA_DICT)
    Map<String, Object> loadByDataDict(NamedArgs args);

    @NamedHandler(ChaosNamedAPI.USERID2USERNAME)
    Map<String, Object> loadByUserName(NamedArgs args);

    @NamedHandler(ChaosNamedAPI.USERID2NICKNAME)
    Map<String, Object> loadByNickname(NamedArgs args);
}
