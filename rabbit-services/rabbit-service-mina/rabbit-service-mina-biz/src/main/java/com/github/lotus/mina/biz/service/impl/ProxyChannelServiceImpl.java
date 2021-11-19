package com.github.lotus.mina.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.GameCard;
import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.manager.ProxyManager;
import com.github.lotus.mina.biz.mapper.ProxyChannelMapper;
import com.github.lotus.mina.biz.mapstruct.ProxyChannelMapping;
import com.github.lotus.mina.biz.pojo.ro.ProxyChannelPagingRo;
import com.github.lotus.mina.biz.pojo.ro.ProxyChannelSaveRo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelComplexVo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
import com.github.lotus.mina.biz.service.ProxyChannelService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.web.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * <p>
 * [代理] 用户隧道表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-11-16
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProxyChannelServiceImpl extends AbstractServiceImpl<ProxyChannelMapper, ProxyChannel>
    implements ProxyChannelService {
    private final ProxyManager proxyManager;
    private final ProxyChannelMapping mapping;

    @Override
    public ProxyChannelInfoVo getChannelInfo(String channelId) {
        ProxyChannel channel = getByChannelIdAndEnabledOn(channelId)
            .orElseThrow(() -> ServiceException.wrap("渠道ID错误"));
        return proxyManager.getFrpChannel(channel);
    }

    @Override
    public Optional<ProxyChannel> getByChannelIdAndEnabledOn(String channelId) {
        return getByChannelIdAndEnabled(channelId, true);
    }

    @Override
    public Optional<ProxyChannel> getByChannelIdAndEnabled(String channelId, Boolean enabled) {
        return lambdaQuery().eq(ProxyChannel::getChannelId, channelId).eq(Objects.nonNull(enabled), ProxyChannel::getEnabled, enabled).oneOpt();
    }

    @Override
    public IPage<ProxyChannelOrdinaryVo> paging(ProxyChannelPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertOrdinary);
    }

    @Override
    public void insertOne(ProxyChannelSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        ProxyChannel entity = mapping.asProxyChannel(ro);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        validInsert(entity);
    }

    @Override
    public void updateOne(Long id, ProxyChannelSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        ProxyChannel entity = mapping.asProxyChannel(ro);
        entity.setChannelId(IdUtil.simpleUUID());
        entity.setId(id);
        entity.setLastUpdater(userId);
        entity.setLastUpdatedAt(now);
        validUpdateById(entity);
    }

    @Override
    public ProxyChannelComplexVo getComplex(Long id) {
        return this.convertComplex(baseMapper.selectById(id));
    }

    @Override
    public void deleteOne(Long id) {
        baseMapper.deleteById(id);
    }

    private ProxyChannelOrdinaryVo convertOrdinary(ProxyChannel entity) {
        return mapping.asProxyChannelOrdinaryVo(entity);
    }

    private ProxyChannelComplexVo convertComplex(ProxyChannel entity) {
        return mapping.asProxyChannelComplexVo(entity);
    }
}
