package com.github.lotus.mina.biz.service;

import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [代理] 用户隧道表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-11-16
 */
public interface ProxyChannelService extends AbstractService<ProxyChannel> {

    ProxyChannelInfoVo getChannelInfo(String channelId);

    Optional<ProxyChannel> getByChannelIdAndEnabledOn(String channelId);

    Optional<ProxyChannel> getByChannelIdAndEnabled(String channelId, Boolean enabled);
}
