package com.github.lotus.chaos.biz.modules.lang.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.github.lotus.chaos.biz.modules.lang.pojo.dto.UnsplashPagingDto;
import com.github.lotus.chaos.biz.modules.lang.pojo.dto.UnsplashPhotoDto;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public List<UnsplashPhotoDto> paging(Integer page, Integer pageSize) {
        String photosUrl = getUnsplashPhotosUrl(page, pageSize);
        HttpRequest request = HttpUtil.createGet(photosUrl);
        HttpResponse response = request.execute();

        String bodyStr = response.body();
        ValidUtils.isTrue(Strings.isNotBlank(bodyStr), "请求失败");
        return JSON.parseArray(bodyStr, UnsplashPhotoDto.class);
    }

    public UnsplashPagingDto search(String keyword, Integer page, Integer pageSize) {
        String photosUrl = getSearchPhotosUrl(keyword, page, pageSize);
        HttpRequest request = HttpUtil.createGet(photosUrl);
        HttpResponse response = request.execute();

        String bodyStr = response.body();
        ValidUtils.isTrue(Strings.isNotBlank(bodyStr), "请求失败");
        return JSON.parseObject(bodyStr, UnsplashPagingDto.class);

    }

    private String getSearchPhotosUrl(String query, Integer page, Integer pageSize) {
        String baseUrl = String.format("https://api.unsplash.com/search/photos?client_id=%s&lang=zh", CLIENT_ID);
        if (Objects.nonNull(page)) {
            baseUrl += "&page=" + page;
        }
        if (Objects.nonNull(pageSize)) {
            baseUrl += "&per_page=" + pageSize;
        }
        baseUrl += "&query=" + LangUtils.getOrDefault(query, "");
        return baseUrl;
    }


    private String getUnsplashPhotosUrl(Integer page, Integer pageSize) {
        String baseUrl = String.format("https://api.unsplash.com/photos?client_id=%s&lang=zh", CLIENT_ID);
        if (Objects.nonNull(page)) {
            baseUrl += "&page=" + page;
        }
        if (Objects.nonNull(pageSize)) {
            baseUrl += "&per_page=" + pageSize;
        }
        return baseUrl;
    }
}
