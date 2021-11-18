package com.github.lotus.mina.biz.support.ytb.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.github.lotus.common.utils.CommonUtils;
import com.github.lotus.mina.biz.constant.YouTubeConstant;
import com.github.lotus.mina.biz.mapstruct.YouTubeMapping;
import com.github.lotus.mina.biz.pojo.dto.UploadYouTubeVideoDto;
import com.github.lotus.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import com.github.lotus.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import com.github.lotus.mina.biz.support.ytb.YouTubeService;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.collect.Lists;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import in.hocg.boot.youtube.autoconfiguration.core.YoutubeBervice;
import in.hocg.boot.youtube.autoconfiguration.core.YoutubeHelper;
import io.lettuce.core.dynamic.Commands;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

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
    private final YouTubeMapping mapping;

    @Override
    public String authorize(String clientId, List<String> scopes) {
        String redirectUri = StrUtil.format("{}/mina/youtube/{}/callback", SpringContext.getBootConfig().getHostname(), clientId);
        return youtubeBervice.authorize(clientId, redirectUri, scopes);
    }

    @Override
    public String authorizeCallback(String clientId, List<String> scopes, String code) {
        String redirectUri = StrUtil.format("{}/mina/youtube/{}/callback", SpringContext.getBootConfig().getHostname(), clientId);
        return youtubeBervice.getCredential(clientId, redirectUri, scopes, code).getAccessToken();
    }

    @SneakyThrows({MalformedURLException.class, IOException.class})
    private void uploadVideo(String clientId, File file, UploadYouTubeVideoDto dto) {
        String thumbnailUrl = dto.getThumbUrl();
        String description = dto.getDescription();
        List<String> tags = dto.getTags();
        String channelId = dto.getChannelId();
        String language = dto.getLanguage();
        String categoryId = dto.getCategoryId();
        String playlistId = dto.getPlaylistId();
        Boolean isModifyMd5 = dto.getIsModifyMd5();
        String title = StrUtil.blankToDefault(dto.getTitle(), file.getName());

        InputStream is;
        if (isModifyMd5) {
            is = FileUtil.getInputStream(CommonUtils.modifyMD5(file.toPath()));
        } else {
            is = FileUtil.getInputStream(file);
        }
        YouTube youtube = youtubeBervice.youtube(clientId, YouTubeConstant.DEFAULT_SCOPES);
        Video video = YoutubeHelper.createVideo();
        video.setSnippet(YoutubeHelper.createVideoSnippet(title, description)
            .setThumbnails(YoutubeHelper.createThumbnailDetails(thumbnailUrl))
            .setDefaultAudioLanguage(language)
            .setCategoryId(categoryId)
            .setDefaultLanguage(language)
            .setChannelId(channelId)
            .setTags(tags));
        InputStreamContent mediaContent = new InputStreamContent("video/*", is);

        // 上传标记
        String parts = StrUtil.join(",", "snippet", "statistics", "status");
        Video rtnVideo = youtube.videos().insert(parts, video, mediaContent).setNotifySubscribers(true).execute();
        System.out.println("\n================== Returned Video ==================\n");
        System.out.println("  - Id: " + rtnVideo.getId());
        System.out.println("  - Title: " + rtnVideo.getSnippet().getTitle());
        System.out.println("  - Tags: " + rtnVideo.getSnippet().getTags());
        System.out.println("  - Privacy Status: " + rtnVideo.getStatus().getPrivacyStatus());
        System.out.println("  - Video Count: " + rtnVideo.getStatistics().getViewCount());

        // 如果需要添加到[播放列表]
        if (StrUtil.isNotBlank(playlistId)) {
            addPlaylistItem(clientId, playlistId, rtnVideo.getId());
        }
    }

    @Override
    public void uploadVideo(String clientId, UploadYouTubeVideoRo dto) {
        String videoUrl = dto.getVideoUrl();
        this.uploadVideo(clientId, CommonUtils.toFile(videoUrl), dto);
    }

    @Override
    public void uploadDir(String clientId, BatchUploadYouTubeVideoRo ro) {
        String localDir = ro.getLocalDir();
        File videoDir = Paths.get(localDir).toFile();
        Lists.newArrayList(Objects.requireNonNull(videoDir.listFiles()))
            .forEach(file -> this.uploadVideo(clientId, file, ro));
    }

    @SneakyThrows
    private void addPlaylistItem(String clientId, String playlistId, String videoId) {
        YouTube youtube = youtubeBervice.youtube(clientId, YouTubeConstant.DEFAULT_SCOPES);
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
    public List<?> channels(String clientId) {
        YouTube youtube = youtubeBervice.youtube(clientId, YouTubeConstant.DEFAULT_SCOPES);
        String part = StrUtil.join(",", "id", "contentDetails");
        return youtube.channels().list(part).setMine(true).execute().getItems();
    }

    @SneakyThrows
    public List<?> playlists(String clientId) {
        YouTube youtube = youtubeBervice.youtube(clientId, YouTubeConstant.DEFAULT_SCOPES);
        String part = StrUtil.join(",", "id", "contentDetails");
        return youtube.playlists().list(part).setMine(true).execute().getItems();
    }
}
