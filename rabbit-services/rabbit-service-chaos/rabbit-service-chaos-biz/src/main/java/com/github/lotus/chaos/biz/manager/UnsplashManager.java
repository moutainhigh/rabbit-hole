package com.github.lotus.chaos.biz.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.github.lotus.chaos.biz.pojo.dto.UnsplashPagingDto;
import com.github.lotus.chaos.biz.pojo.dto.UnsplashPhotoDto;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperTopicVo;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
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
    private static final String CLIENT_ID = "ntccF6YUpBY_4-Z1Ko4VIZ2xFEJxUYWfgABXyQb4WwI";

    public List<WallpaperTopicVo> topics() {
        List<WallpaperTopicVo> result = Lists.newArrayList();
        result.add(new WallpaperTopicVo().setTitle("推荐").setCode("wallpapers")
            .setColor("#26260c").setBlurHash("LEBpFJRk5TR+5toJ^ia#0KfPIoxY").setImageUrl("https://images.unsplash.com/12/gladiator.jpg?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("科技").setCode("technology")
            .setColor("#d9d9d9").setImageUrl("https://images.unsplash.com/27/type-set.jpg?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("旅行").setCode("travel")
            .setColor("#d9f3f3").setImageUrl("https://images.unsplash.com/24/flashlight.jpg?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("纹理").setCode("textures-patterns")
            .setColor("#a68c73").setImageUrl("https://images.unsplash.com/22/brick-wall.JPG?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("动物").setCode("animals")
            .setColor("#404040").setImageUrl("https://images.unsplash.com/44/MIbCzcvxQdahamZSNQ26_12082014-IMG_3526.jpg?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("食物").setCode("food-drink")
            .setColor("#f3f3f3").setImageUrl("https://images.unsplash.com/33/IR8nDBZETv6aM6HdJ7RD_IMG_5784.jpg?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("运动").setCode("athletics")
            .setColor("#735940").setImageUrl("https://images.unsplash.com/reserve/bIdO4DDS4qwVF6pHN4qr__MG_1605.jpg?ixlib=rb-1.2.1"));
        result.add(new WallpaperTopicVo().setTitle("灵感").setCode("spirituality")
            .setColor("#c0a6a6").setImageUrl("https://images.unsplash.com/16/unsplash_526360a842e20_1.JPG?ixlib=rb-1.2.1"));
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
