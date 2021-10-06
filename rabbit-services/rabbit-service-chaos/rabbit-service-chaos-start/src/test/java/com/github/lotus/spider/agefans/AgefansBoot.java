package com.github.lotus.spider.agefans;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.qiniu.util.UrlUtils;

import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2021/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AgefansBoot {
    private static String cookieStr = "fa_c=1;fa_t=1;";

    public static void main(String[] args) {
        String url = "https://www.agefans.cc/play/20200294?playid=3_19";
        String videoUrl = getVideoUrl(url);
        videoUrl = getVideoUrl(url);
        System.out.println(videoUrl);
    }

    public static void fillCookie(HttpResponse execute) {
        for (HttpCookie cookie : execute.getCookies()) {
            cookieStr += (cookie.getName() + "=" + cookie.getValue() + ";");
        }
    }

    public static String getVideoUrl(String url) {
        Map<String, String> queryMaps = HttpUtil.decodeParamMap(url, Charset.defaultCharset());
        int startIndex = url.lastIndexOf("/") + 1;
        int endIndex = url.lastIndexOf("?");
        String videoId = StrUtil.sub(url, startIndex, endIndex);

        String playid = queryMaps.get("playid");
        String[] channel = playid.split("_");
        String channelId = channel[0];
        String index = channel[1];
        double r = Math.random();
        String getPlayUrl = StrUtil.format("https://www.agefans.cc/_getplay?aid={}&playindex={}&epindex={}&r={}", videoId, channelId, index, r);

        long t1 = System.currentTimeMillis();
        long k1 = Math.round(t1 / 1000) >> 5;
        cookieStr += ("t1=" + t1 + ";");
        cookieStr += ("k1=" + k1 + ";");
        HttpRequest request = HttpUtil.createGet(getPlayUrl);
        request.enableDefaultCookie();
        request.setMaxRedirectCount(10);
        request.setFollowRedirects(true);
        request.cookie(cookieStr);
        request.header("X-Requested-With", "XMLHttpRequest");
        request.header("Referer", url);
        request.header("Host", "www.agefans.cc");

        request.header("Accept", "*/*");
        request.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36");
        // vurl
        HttpResponse response = request.execute();
        response.getCookieStr();
        String body = response.body();
        try {
            fillCookie(response);
            return URLUtil.decode(JSON.parseObject(body).getString("vurl"));
        } catch (Exception e) {
            return null;
        }
    }
}
