package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import in.hocg.rabbit.mina.biz.manager.ProxyManager;
import in.hocg.rabbit.mina.biz.mapper.ProxyChannelMapper;
import in.hocg.rabbit.mina.biz.mapstruct.ProxyChannelMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.ProxyChannelPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.ProxyChannelSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelInfoVo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
import in.hocg.rabbit.mina.biz.service.ProxyChannelService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.rabbit.mina.biz.support.channel.ChannelHelper;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
        saveOne(null, ro);
    }

    @Override
    public void updateOne(Long id, ProxyChannelSaveRo ro) {
        saveOne(id, ro);
    }

    @Override
    public ProxyChannelComplexVo getComplex(Long id) {
        return this.convertComplex(baseMapper.selectById(id));
    }

    private void saveOne(Long id, ProxyChannelSaveRo ro) {
        ProxyChannel entity = mapping.asProxyChannel(ro);
        entity.setId(id);
        if (Objects.isNull(id)) {
            if (StrUtil.isBlank(ro.getChannelId())) {
                entity.setChannelId(IdUtil.simpleUUID());
            }
            entity.setSuffix(ChannelHelper.CHANNEL_SUFFIX);
        }
        validInsertOrUpdate(entity);
    }

    @Override
    public void validEntity(ProxyChannel entity) {
        super.validEntity(entity);

        Long id = entity.getId();
        String channelId = entity.getChannelId();
        if (Objects.nonNull(channelId)) {
            boolean hasChannelId = has(ProxyChannel::getChannelId, channelId, ProxyChannel::getId, id);
            Assert.isFalse(hasChannelId, "隧道编号: {} 已存在", channelId);
        }

    }

    private ProxyChannelOrdinaryVo convertOrdinary(ProxyChannel entity) {
        return mapping.asProxyChannelOrdinaryVo(entity);
    }

    private ProxyChannelComplexVo convertComplex(ProxyChannel entity) {
        return mapping.asProxyChannelComplexVo(entity);
    }
}
