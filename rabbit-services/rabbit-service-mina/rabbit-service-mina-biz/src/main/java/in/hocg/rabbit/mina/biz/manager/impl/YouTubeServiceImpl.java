package in.hocg.rabbit.mina.biz.manager.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.google.api.client.auth.oauth2.Credential;
import com.google.common.collect.Maps;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.boot.youtube.autoconfiguration.utils.YoutubeUtils;
import in.hocg.boot.youtube.autoconfiguration.utils.data.YouTubeChannel;
import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.common.utils.CommonUtils;
import in.hocg.rabbit.mina.biz.constant.YouTubeConstant;
import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeClientCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.collect.Lists;
import in.hocg.boot.youtube.autoconfiguration.core.YoutubeBervice;
import in.hocg.boot.youtube.autoconfiguration.core.YoutubeHelper;
import in.hocg.rabbit.mina.biz.pojo.vo.YouTubeClientVo;
import in.hocg.rabbit.mina.biz.service.Y2bChannelService;
import in.hocg.rabbit.mina.biz.support.YouTubeHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class YouTubeServiceImpl implements YouTubeService {
    private final YoutubeBervice youtubeBervice;
    private final Y2bChannelService channelService;
    private final FileServiceApi fileServiceApi;

    @Override
    public String authorize(String clientId, List<String> scopes) {
        return youtubeBervice.authorize(clientId, YouTubeHelper.getRedirectUri(clientId), scopes);
    }

    @Override
    public void authorizeCallback(String clientId, List<String> scopes, String code) {
        youtubeBervice.getCredential(clientId, YouTubeHelper.getRedirectUri(clientId), scopes, code,
            credential -> YouTubeHelper.asChannelFlag(channelService.rebind(clientId, YoutubeUtils.getYouTubeChannel(credential))));
    }

    @Override
    @SneakyThrows
    public void uploadVideo(Long channelId, File videoFile, UploadY2bDto dto) {
        Y2bChannel channel = channelService.getById(channelId);
        YouTube youtube = getYoutube(channel);

        String thumbnailUrl = dto.getThumbnailUrl();

        String description = dto.getDescription();
        List<String> tags = dto.getTags();
        String ytbChannelId = channel.getChannelId();
        String language = dto.getLanguage();
        String categoryId = dto.getCategoryId();
        String playlistId = dto.getPlaylistId();
        String title = StrUtil.blankToDefault(dto.getTitle(), videoFile.getName());

        Video video = YoutubeHelper.createVideo();
        video.setSnippet(YoutubeHelper.createVideoSnippet(title, description)
            .setThumbnails(LangUtils.callIfNotNull(thumbnailUrl, YoutubeHelper::createThumbnailDetails).orElse(null))
            .setDefaultAudioLanguage(language)
            .setCategoryId(categoryId)
            .setDefaultLanguage(language)
            .setChannelId(ytbChannelId)
            .setTags(tags));

        Map<String, UploadY2bDto.LocalTitle> localMaps = dto.getLocalMaps();
        if (CollUtil.isNotEmpty(localMaps)) {
            HashMap<String, VideoLocalization> map = Maps.newHashMap();
            localMaps.forEach((key, value) -> map.put(key, new VideoLocalization().setTitle(value.getTitle()).setDescription(value.getDescription())));
            video.setLocalizations(map);
        }

        InputStreamContent mediaContent = new InputStreamContent("video/*", FileUtil.getInputStream(videoFile));

        // 上传标记
        String parts = StrUtil.join(",", "snippet", "statistics", "status");
        Video rtnVideo = youtube.videos().insert(parts, video, mediaContent).setNotifySubscribers(true).execute();
        log.debug("================== Returned Video ==================");
        log.debug("  - Id: " + rtnVideo.getId());
        log.debug("  - Title: " + rtnVideo.getSnippet().getTitle());
        log.debug("  - Tags: " + rtnVideo.getSnippet().getTags());
        log.debug("  - Privacy Status: " + rtnVideo.getStatus().getPrivacyStatus());
        log.debug("  - Video Count: " + rtnVideo.getStatistics().getViewCount());

        // 如果需要添加到[播放列表]
        if (StrUtil.isNotBlank(playlistId)) {
            addPlaylistItem(channel, playlistId, rtnVideo.getId());
        }
    }

    @Override
    public void uploadVideo(Long channelId, UploadYouTubeVideoRo ro) {
        String videoUrl = ro.getVideoUrl();
        this.uploadVideo(channelId, CommonUtils.toFile(videoUrl), ro);
    }

    @Override
    public void uploadDir(Long channelId, BatchUploadYouTubeVideoRo ro) {
        String localDir = ro.getLocalDir();
        File videoDir = Paths.get(localDir).toFile();
        Lists.newArrayList(Objects.requireNonNull(videoDir.listFiles()))
            .forEach(file -> this.uploadVideo(channelId, file, ro));
    }

    @Override
    public List<YouTubeClientVo> clientComplete(YouTubeClientCompleteRo ro) {
        return youtubeBervice.getClientConfigs().values().stream()
            .map(clientConfig -> new YouTubeClientVo().setApplicationName(clientConfig.getApplicationName())
                .setId(clientConfig.getClientId())).collect(Collectors.toList());
    }

    @Override
    public YouTubeChannel getChannel(Long channelId) {
        Credential credential = getCredential(channelService.getById(channelId))
            .orElseThrow(() -> ServiceException.wrap("获取认证信息失败"));
        return YoutubeUtils.getYouTubeChannel(credential);
    }

    @SneakyThrows
    private void addPlaylistItem(Y2bChannel channel, String playlistId, String videoId) {
        YouTube youtube = getYoutube(channel);
        ResourceId resourceId = new ResourceId();
        resourceId.setKind("youtube#video");
        resourceId.setVideoId(videoId);

        PlaylistItemSnippet snippet = new PlaylistItemSnippet();
        snippet.setPlaylistId(playlistId);
        snippet.setResourceId(resourceId);

        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setSnippet(snippet);

        YouTube.PlaylistItems.Insert insertRo = youtube.playlistItems().insert("snippet,contentDetails", playlistItem);
        PlaylistItem rtnInsert = insertRo.execute();

        System.out.println("New PlaylistItem name: " + rtnInsert.getSnippet().getTitle());
        System.out.println(" - Video id: " + rtnInsert.getSnippet().getResourceId().getVideoId());
        System.out.println(" - Posted: " + rtnInsert.getSnippet().getPublishedAt());
        System.out.println(" - Channel: " + rtnInsert.getSnippet().getChannelId());
        // rtnInsert.getId();
    }

    @SneakyThrows
    public List<?> channels(Y2bChannel channel) {
        YouTube youtube = getYoutube(channel);
        String part = StrUtil.join(",", "id", "contentDetails");
        return youtube.channels().list(part).setMine(true).execute().getItems();
    }

    @SneakyThrows
    public List<?> playlists(Y2bChannel channel) {
        YouTube youtube = getYoutube(channel);
        String part = StrUtil.join(",", "id", "contentDetails");
        return youtube.playlists().list(part).setMine(true).execute().getItems();
    }

    private YouTube getYoutube(Y2bChannel channel) {
        String clientId = channel.getClientId();
        String channelFlag = YouTubeHelper.asChannelFlag(channel.getId());
        return youtubeBervice.youtube(clientId, channelFlag, YouTubeConstant.DEFAULT_SCOPES);
    }

    private Optional<Credential> getCredential(Y2bChannel channel) {
        if (Objects.isNull(channel)) {
            return Optional.empty();
        }
        return youtubeBervice.loadCredential(channel.getClientId(), YouTubeHelper.asChannelFlag(channel.getId()));
    }
}
