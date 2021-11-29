package com.github.lotus.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.pojo.ro.ProxyChannelPagingRo;
import com.github.lotus.mina.biz.pojo.ro.ProxyChannelSaveRo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelComplexVo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
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

    /**
     * 获取开启的代理隧道
     *
     * @param channelId 隧道ID
     * @return 代理隧道信息
     */
    Optional<ProxyChannel> getByChannelIdAndEnabledOn(String channelId);

    Optional<ProxyChannel> getByChannelIdAndEnabled(String channelId, Boolean enabled);

    IPage<ProxyChannelOrdinaryVo> paging(ProxyChannelPagingRo ro);

    void insertOne(ProxyChannelSaveRo ro);

    void updateOne(Long id, ProxyChannelSaveRo ro);

    ProxyChannelComplexVo getComplex(Long id);

    void deleteOne(Long id);
}
