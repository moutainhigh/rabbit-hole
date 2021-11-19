package com.github.lotus.mina.biz.support.channel.frp;

import cn.hutool.core.util.StrUtil;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;
import lombok.experimental.UtilityClass;

import java.util.StringJoiner;

/**
 * Created by hocgin on 2021/11/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class FrpHelper {

    public static String toFrpcIni(ProxyChannelInfoVo.ProxyConfig config) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator())
            .add("[common]")
            .add(StrUtil.format("server_addr = {}", config.getServerAddr()))
            .add(StrUtil.format("server_port = {}", config.getServerPort()));
        for (ProxyChannelInfoVo.ProxyConfig.Proxy proxy : config.getProxies()) {
            joiner.add("")
                .add(StrUtil.format("[{}]", proxy.getName()))
                .add(StrUtil.format("type = {}", proxy.getType()))
                .add(StrUtil.format("custom_domains = {}", proxy.getCustomDomains()))
                .add(StrUtil.format("local_ip = {}", proxy.getLocalIp()))
                .add(StrUtil.format("local_port = {}", proxy.getLocalPort()))
                .add(StrUtil.format("meta_token = {}", proxy.getName()))
                .add("")
            ;
        }
        return joiner.toString();
    }
}
