package in.hocg.rabbit.mina.biz.service.impl;

import in.hocg.boot.youtube.autoconfiguration.utils.data.CredentialChannel;
import in.hocg.boot.youtube.autoconfiguration.utils.data.YouTubeChannel;
import in.hocg.rabbit.mina.biz.entity.Channel;
import in.hocg.rabbit.mina.biz.mapper.ChannelMapper;
import in.hocg.rabbit.mina.biz.service.ChannelService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>
 * [YouTube] YouTube频道表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-03-30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ChannelServiceImpl extends AbstractServiceImpl<ChannelMapper, Channel> implements ChannelService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long rebind(String clientId, YouTubeChannel youTubeChannel) {
        String channelId = youTubeChannel.getChannelId();
        Channel channel = getByClientIdAndChannelId(clientId, channelId).orElseGet(Channel::new);
        channel.setChannelId(channelId)
            .setClientId(clientId)
            .setTitle(youTubeChannel.getTitle())
            .setImageUrl(youTubeChannel.getImageUrl())
            .setUrl(youTubeChannel.getUrl());
        saveOrUpdate(channel);
        return channel.getId();
    }


    private Optional<Channel> getByClientIdAndChannelId(String clientId, String channelId) {
        return lambdaQuery().eq(Channel::getClientId, clientId).eq(Channel::getChannelId, channelId).oneOpt();
    }

}
