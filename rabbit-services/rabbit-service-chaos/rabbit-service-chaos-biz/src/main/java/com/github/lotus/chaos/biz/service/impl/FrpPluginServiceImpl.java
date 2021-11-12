package com.github.lotus.chaos.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.github.lotus.chaos.biz.service.FrpPluginService;
import com.github.lotus.chaos.biz.support.frp.ro.*;
import com.github.lotus.chaos.biz.support.frp.vo.FrpResult;
import com.github.lotus.chaos.biz.support.frp.vo.NewProxyFrpVo;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    public FrpResult<?> login(LoginFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    public FrpResult<?> newProxy(NewProxyFrpRo ro) {
        // 后台生成
        String proxyName = ro.getProxyName();
        // 后台配置
        String proxyType = ro.getProxyType();
        // 后台配置
        String subdomain = ro.getSubdomain();
        // 后台配置
        Integer remotePort = ro.getRemotePort();


        NewProxyFrpRo.Metas metas = ro.getMetas();
//        Assert.notNull(metas, "配置信息错误");
//        String authKey = metas.getAuthKey();
//        boolean hasPass = Objects.nonNull(authKey);

//        if (!hasPass) {
//            return FrpResult.reject("认证失败");
//        }

        NewProxyFrpVo content = BeanUtil.copyProperties(ro, NewProxyFrpVo.class);
        content.setProxyName(proxyName);
        content.setProxyType(proxyType);
        content.setSubdomain(subdomain);
        content.setRemotePort(remotePort);
        return FrpResult.pass(content);
    }

    @Override
    public FrpResult<?> ping(PingFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    public FrpResult<?> newWorkConn(NewWorkConnFrpRo ro) {
        return FrpResult.pass();
    }

    @Override
    public FrpResult<?> newUserConn(NewUserConnFrpRo content) {
        return FrpResult.pass();
    }
}
