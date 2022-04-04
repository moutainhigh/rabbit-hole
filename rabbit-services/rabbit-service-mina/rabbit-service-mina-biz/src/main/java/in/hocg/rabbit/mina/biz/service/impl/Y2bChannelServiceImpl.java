package in.hocg.rabbit.mina.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.youtube.autoconfiguration.utils.data.YouTubeChannel;
import in.hocg.rabbit.common.utils.CommonUtils;
import in.hocg.rabbit.cv.api.CvServiceApi;
import in.hocg.rabbit.mina.biz.convert.Y2bChannelConvert;
import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import in.hocg.rabbit.mina.biz.manager.VideoService;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.rabbit.mina.biz.mapper.Y2bChannelMapper;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadCollectionRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelPageRo;
import in.hocg.rabbit.mina.biz.pojo.vo.Y2bChannelCompleteVo;
import in.hocg.rabbit.mina.biz.props.MinaProperties;
import in.hocg.rabbit.mina.biz.service.Y2bChannelService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final VideoService videoService;
    private final MinaProperties properties;

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

    @Override
    public void uploadCollection(Long channelId, UploadCollectionRo ro) {
        String videoUrl = ro.getVideoUrl();
        Path diskPath = Path.of(properties.getDiskPath());

        // 1. 获取视频地址
        List<VideoInfo> videos = videoService.getVideoWithCollection(videoUrl);
        if (CollUtil.isEmpty(videos)) {
            return;
        }
        VideoInfo videoInfo = videos.get(0);
        String collectionName = videoInfo.getTitle();

        // 2. 下载视频
        List<File> files = videoService.download(LangUtils.toList(videos, VideoInfo::getUrl),
            diskPath.resolve(collectionName).toFile());

        // 3. 合并
        String title = StrUtil.format("{}({}~{})", collectionName, 0, files.size());
        Path mergeFile = diskPath.resolve(title);
        videoService.merge(files, mergeFile.toFile());

        // 4. 调整文件
        videoService.modifyFile(mergeFile.toFile());

        // 4. 上传
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbFile(null);
        List<String> tags = Lists.newArrayList(videoInfo.getKeywords());
        tags.addAll(ro.getAddTags());
        options.setTags(tags.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        options.setDescription(videoInfo.getDesc());
        videoService.upload(channelId, mergeFile.toFile(), options);
    }


    private Optional<Y2bChannel> getByClientIdAndChannelId(String clientId, String channelId) {
        return lambdaQuery().eq(Y2bChannel::getClientId, clientId).eq(Y2bChannel::getChannelId, channelId).oneOpt();
    }

}
