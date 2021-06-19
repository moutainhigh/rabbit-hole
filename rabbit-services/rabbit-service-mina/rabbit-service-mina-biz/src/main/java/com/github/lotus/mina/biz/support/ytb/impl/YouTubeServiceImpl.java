package com.github.lotus.mina.biz.support.ytb.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import com.github.lotus.common.utils.CommonUtils;
import com.github.lotus.mina.api.pojo.ro.UploadYouTubeRo;
import com.github.lotus.mina.biz.mapstruct.YouTubeMapping;
import com.github.lotus.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import com.github.lotus.mina.biz.support.ytb.YouTubeService;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;
import in.hocg.boot.youtube.autoconfiguration.core.YoutubeBervice;
import in.hocg.boot.youtube.autoconfiguration.properties.YoutubeProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.function.BiConsumer;

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
    private final YoutubeBervice youtubeServiceApi;
    private final YouTubeMapping mapping;

    @Override
    @SneakyThrows
    public String upload(UploadYouTubeVideoRo ro) {
        String clientId = ro.getClientId();
        String fromUrl = ro.getFromUrl();
        Boolean isModifyMd5 = Assert.notNull(ro.getIsModifyMd5());
        String privacyStatus = ro.getPrivacyStatus();

        InputStream is;
        if (isModifyMd5) {
            is = FileUtil.getInputStream(CommonUtils.modifyMD5(CommonUtils.toFile2(fromUrl)));
        } else {
            is = URI.create(fromUrl).toURL().openStream();
        }

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");
        StringBuilder sb = new StringBuilder("https://www.youtube.com/watch?v=");
        youtubeServiceApi.youtube(clientId, scopes, new BiConsumer<YoutubeProperties.ClientConfig, YouTube>() {
            @Override
            @SneakyThrows
            public void accept(YoutubeProperties.ClientConfig clientConfig, YouTube youTube) {
                VideoStatus status = new VideoStatus()
                    .setPrivacyStatus(privacyStatus);
                VideoSnippet snippet = new VideoSnippet()
                    .setTitle(ro.getTitle())
                    .setDescription(ro.getDescription())
                    .setTags(ro.getTags());

                Video video = new Video().setStatus(status).setSnippet(snippet);
                YouTube.Videos.Insert videoInsert = youTube.videos().insert("snippet,statistics,status", video, new InputStreamContent("video/*", is));

                MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();
                uploader.setDirectUploadEnabled(false);

                Video returnedVideo = videoInsert.execute();
                String rid = returnedVideo.getId();
                log.info("上传视频信息: [{}]", rid);
                sb.append(rid);
            }
        });
        return sb.toString();
    }

    @Override
    public String authorize(String clientId, List<String> scopes, String redirectUri) {
        return youtubeServiceApi.authorize(clientId, redirectUri, scopes);
    }

    @Override
    public String getCredential(String clientId, String redirectUri, List<String> scopes, String code) {
        return youtubeServiceApi.getCredential(clientId, redirectUri, scopes, code).getAccessToken();
    }

    @Override
    public void uploadVideo(UploadYouTubeRo ro) {
        this.upload(mapping.asUploadYouTubeVideoRo(ro));
    }
}
