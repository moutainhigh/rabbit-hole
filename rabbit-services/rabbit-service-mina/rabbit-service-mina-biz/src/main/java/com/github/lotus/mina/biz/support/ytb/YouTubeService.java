package com.github.lotus.mina.biz.support.ytb;

import com.github.lotus.mina.biz.pojo.dto.UploadYouTubeVideoDto;
import com.github.lotus.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import com.github.lotus.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface YouTubeService {

    /**
     * 授权应用
     *
     * @param clientId
     * @param scopes
     * @return
     */
    String authorize(String clientId, List<String> scopes);

    /**
     * 授权回调
     *
     * @param clientId
     * @param scopes
     * @param code
     * @return
     */
    String authorizeCallback(String clientId, List<String> scopes, String code);

    @Async
    void uploadVideo(String clientId, UploadYouTubeVideoRo ro);

    @Async
    void uploadDir(String clientId, BatchUploadYouTubeVideoRo ro);
}
