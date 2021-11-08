package com.github.lotus.chaos.biz.service.impl;

import com.github.lotus.chaos.biz.service.FrpPluginService;
import com.github.lotus.chaos.biz.support.frp.ro.*;
import com.github.lotus.chaos.biz.support.frp.vo.FrpResult;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FrpPluginServiceImpl implements FrpPluginService {

    @Override
    @UseLogger(value = "frp login")
    public FrpResult<?> login(LoginFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    @UseLogger(value = "frp newProxy")
    public FrpResult<?> newProxy(NewProxyFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    @UseLogger(value = "frp ping")
    public FrpResult<?> ping(PingFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    @UseLogger(value = "frp newWorkConn")
    public FrpResult<?> newWorkConn(NewWorkConnFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    @UseLogger(value = "frp newUserConn")
    public FrpResult<?> newUserConn(NewUserConnFrpRo content) {
        return FrpResult.pass();
    }
}
