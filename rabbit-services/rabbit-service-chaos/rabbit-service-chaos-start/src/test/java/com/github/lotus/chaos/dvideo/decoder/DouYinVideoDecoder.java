package com.github.lotus.chaos.dvideo.decoder;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.lotus.chaos.dvideo.VideoDecoder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class DouYinVideoDecoder implements VideoDecoder {
    private static final String VIDEO_PATH = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";

    @Override
    public String decoding(String url) throws Exception {
        Connection con = Jsoup.connect(filterUrl(url));
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String videoUrl = VIDEO_PATH + getItemId(resp.url().toString());
        String jsonStr = Jsoup.connect(videoUrl)
            .headers(headers())
            .ignoreContentType(true).execute().body();

        JSONObject json = JSONObject.parseObject(jsonStr);
        String videoAddress = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
        String title = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info").getString("share_title");
        videoAddress = videoAddress.replaceAll("playwm", "play");

        String noVideoUrl = HttpUtil.createGet(videoAddress)
            .addHeaders(headers())
            .execute().header("Location");
        log.info("视频名称: [{}], 无水印链接: [{}]", title, noVideoUrl);

        return noVideoUrl;
    }

    private static String getItemId(String url) {
        int start = url.indexOf("/video/") + 7;
        int end = url.lastIndexOf("/");
        return url.substring(start, end);
    }

}
