package com.github.lotus.chaos.biz.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.github.lotus.chaos.biz.pojo.dto.UnsplashPagingDto;
import com.github.lotus.chaos.biz.pojo.dto.UnsplashPhotoDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.datastruct.KeyValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UnsplashManager {
    private static final String CLIENT_ID = "PHul2POuZ6gLBi0wL3mP6GZ_beuXPtMeF5hN4SDD6LA";

    public List<KeyValue> topics() {
        Map<String, String> topics = Maps.newHashMap();
        topics.put("推荐", "wallpapers");
        topics.put("科技", "technology");
        topics.put("旅行", "travel");
        topics.put("纹理", "textures-patterns");
        topics.put("动物", "animals");
        topics.put("食物", "food-drink");
        topics.put("运动", "athletics");
        topics.put("灵感", "spirituality");

        List<KeyValue> result = Lists.newArrayList();
        for (Map.Entry<String, String> entry : topics.entrySet()) {
            result.add(new KeyValue().setKey(entry.getKey()).setValue(entry.getValue()));
        }

        return result;
    }

    public List<UnsplashPhotoDto> paging(Integer page, Integer pageSize) {
        String photosUrl = getPageUrl("https://api.unsplash.com/photos", page, pageSize);
        HttpRequest request = HttpUtil.createGet(photosUrl);
        HttpResponse response = request.execute();

        String bodyStr = response.body();
        ValidUtils.isTrue(Strings.isNotBlank(bodyStr), "请求失败");
        return JSON.parseArray(bodyStr, UnsplashPhotoDto.class);
    }

    public UnsplashPagingDto search(String keyword, Integer page, Integer pageSize) {
        String photosUrl = getPageUrl("https://api.unsplash.com/search/photos", page, pageSize, new HashMap<String, String>() {{
            put("query", keyword);
        }});
        HttpRequest request = HttpUtil.createGet(photosUrl);
        HttpResponse response = request.execute();

        String bodyStr = response.body();
        ValidUtils.isTrue(Strings.isNotBlank(bodyStr), "请求失败");
        return JSON.parseObject(bodyStr, UnsplashPagingDto.class);
    }

    public List<UnsplashPhotoDto> pagingByTopic(String topicId, Integer page, Integer pageSize) {
        String photosUrl = getPageUrl(String.format("https://api.unsplash.com/topics/%s/photos", topicId), page, pageSize);
        HttpRequest request = HttpUtil.createGet(photosUrl);
        HttpResponse response = request.execute();

        String bodyStr = response.body();
        ValidUtils.notBlank(bodyStr, "请求失败");
        return JSON.parseArray(bodyStr, UnsplashPhotoDto.class);
    }

    public UnsplashPhotoDto random() {
        String photosUrl = getWrapperClientId("https://api.unsplash.com/photos/random");
        HttpRequest request = HttpUtil.createGet(photosUrl);
        HttpResponse response = request.execute();

        String bodyStr = response.body();
        ValidUtils.notBlank(bodyStr, "请求失败");
        return JSON.parseObject(bodyStr, UnsplashPhotoDto.class);
    }

    private String getWrapperClientId(String url) {
        return String.format("%s?client_id=%s&lang=zh", url, CLIENT_ID);
    }

    private String getPageUrl(String url, Integer page, Integer pageSize) {
        return getPageUrl(url, page, pageSize, new HashMap<>());
    }

    private String getPageUrl(String url, Integer page, Integer pageSize, Map<String, String> params) {
        StringBuilder baseUrl = new StringBuilder(getWrapperClientId(url));
        if (Objects.nonNull(page)) {
            baseUrl.append("&page=").append(page);
        }
        if (Objects.nonNull(pageSize)) {
            baseUrl.append("&per_page=").append(pageSize);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = LangUtils.getOrDefault(entry.getValue(), "");
            baseUrl.append(String.format("&%s=%s", key, value));
        }
        return baseUrl.toString();
    }
}
