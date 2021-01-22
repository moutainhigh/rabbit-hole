package com.github.lotus.chaos.biz.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 * Created by hocgin on 2021/1/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ImageManager {
    public static void main(String[] args) {
        String api = "https://api.remove.bg/v1.0/removebg";
        Map<String, String> headers = Maps.newHashMap();
        headers.put("X-Api-Key", "Q3dwwTQuLj9etJhazJt5Hu5S");

        Map<String, Object> formMap = Maps.newHashMap();
        formMap.put("image_url", "http://cdn.hocgin.top/9e105c5376d5cfdcc3ef2dca62f5fafd.png");
//        formMap.put("size", "auto");
        formMap.put("type", "person");
        formMap.put("size", "625×400");
        // PNG JPG
        formMap.put("format", "");
        formMap.put("bg_color", "");
        HttpRequest request = HttpUtil.createPost(api)
            .addHeaders(headers)
            .form(formMap);

        HttpResponse response = request.execute();
        response.writeBody(new File("/Users/hocgin/Downloads/xx.png"));
    }
}
