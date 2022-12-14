package in.hocg.rabbit.mina.biz.manager.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.common.utils.Md5Utils;
import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import in.hocg.rabbit.mina.biz.manager.ProxyManager;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelInfoVo;
import in.hocg.rabbit.mina.biz.support.channel.ChannelHelper;
import in.hocg.rabbit.mina.biz.support.channel.frp.FrpHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProxyManagerImpl implements ProxyManager {

    @Override
    public ProxyChannelInfoVo getFrpChannel(ProxyChannel channel) {
        String domain = ChannelHelper.getDomain(channel);

        ProxyChannelInfoVo.ProxyConfig config = new ProxyChannelInfoVo.ProxyConfig()
            .setServerAddr("frps.hocgin.top")
            .setServerPort(30902)
//            本地开发环境
//            .setServerAddr("127.0.0.1")
//            .setServerPort(7000)
            .setProxies(List.of(new ProxyChannelInfoVo.ProxyConfig.Proxy()
                .setName(channel.getChannelId())
                .setType(channel.getType())
                .setCustomDomains(domain)
                .setLocalIp(channel.getLocalIp())
                .setLocalPort(channel.getLocalPort()))
            );
        return new ProxyChannelInfoVo()
            .setMd5(Md5Utils.getMD5(JSONUtil.toJsonStr(config), "utf-8"))
            .setConfig(config)
            .setCnvStr(FrpHelper.toFrpcIni(config));
    }

}
