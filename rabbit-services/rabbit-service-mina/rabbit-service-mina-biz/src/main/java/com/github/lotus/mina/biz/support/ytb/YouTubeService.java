package com.github.lotus.mina.biz.support.ytb;

import com.github.lotus.mina.biz.pojo.ro.UploadYouTubeVideoRo;

import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface YouTubeService {

    String upload(UploadYouTubeVideoRo ro);

    String authorize(String clientId, List<String> scopes, String redirectUri);

    String getCredential(String clientId, String redirectUri, List<String> scopes, String code);
}
