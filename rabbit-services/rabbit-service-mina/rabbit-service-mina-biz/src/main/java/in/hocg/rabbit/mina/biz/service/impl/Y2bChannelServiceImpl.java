package in.hocg.rabbit.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.convert.Y2bChannelConvert;
import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.rabbit.mina.biz.mapper.Y2bChannelMapper;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelPageRo;
import in.hocg.rabbit.mina.biz.pojo.vo.Y2bChannelCompleteVo;
import in.hocg.rabbit.mina.biz.service.Y2bChannelService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
public class Y2bChannelServiceImpl extends AbstractServiceImpl<Y2bChannelMapper, Y2bChannel> implements Y2bChannelService {
    private final Y2bChannelConvert convert;
    private final YouTubeService youTubeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long rebind(String clientId, in.hocg.boot.youtube.autoconfiguration.utils.data.YouTubeChannel youTubeChannel) {
        String channelId = youTubeChannel.getChannelId();
        Y2bChannel channel = getByClientIdAndChannelId(clientId, channelId).orElseGet(Y2bChannel::new);
        channel.setChannelId(channelId)
            .setClientId(clientId)
            .setTitle(youTubeChannel.getTitle())
            .setImageUrl(youTubeChannel.getImageUrl())
            .setUrl(youTubeChannel.getUrl());
        saveOrUpdate(channel);
        return channel.getId();
    }

    @Override
    public List<Y2bChannelCompleteVo> complete(YouTubeChannelCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(convert::asCompleteVo).getRecords();
    }

    @Override
    public IPage<Y2bChannelCompleteVo> paging(YouTubeChannelPageRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asCompleteVo);
    }

    @Override
    public Object ping(Long channelId) {
        return youTubeService.getChannel(channelId);
    }


    private Optional<Y2bChannel> getByClientIdAndChannelId(String clientId, String channelId) {
        return lambdaQuery().eq(Y2bChannel::getClientId, clientId).eq(Y2bChannel::getChannelId, channelId).oneOpt();
    }

}
