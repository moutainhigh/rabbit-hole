package in.hocg.rabbit.mina.biz.support.down.decoder;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import in.hocg.rabbit.mina.biz.support.down.VideoDecoder;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class DouYinVideoDecoder implements VideoDecoder {
    private static final String VIDEO_PATH = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids={}";
    private static final String VIDEO_LIST_PATH = "https://www.iesdouyin.com/web/api/mix/item/list/?mix_id={}&count={}&cursor={}";

    @Override
    public String decoding(String url) throws Exception {
        Connection con = Jsoup.connect(filterUrl(url));
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String urlStr = resp.url().toString();
        String itemId = StrUtil.subBetween(urlStr, "/video/", "?");

        String videoUrl = StrUtil.format(VIDEO_PATH, itemId);
        String jsonStr = Jsoup.connect(videoUrl)
            .headers(headers())
            .ignoreContentType(true).execute().body();

        cn.hutool.json.JSONObject json = JSONUtil.parseObj(jsonStr);
        String videoAddress = json.getJSONArray("item_list").getJSONObject(0)
            .getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
        String title = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info").getStr("share_title");
        videoAddress = videoAddress.replaceAll("playwm", "play");


        String noVideoUrl = HttpUtil.createGet(videoAddress)
            .addHeaders(headers())
            .execute().header("Location");
        log.info("视频名称: [{}], 无水印链接: [{}]", title, noVideoUrl);

        return noVideoUrl;
    }

    @SneakyThrows
    @Override
    public List<VideoInfo> list(String url) {
        List<VideoInfo> result = Lists.newArrayList();

        Connection con = Jsoup.connect(filterUrl(url));
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String urlStr = resp.url().toString();
        String itemId = StrUtil.subBetween(urlStr, "/detail/", "/");

        String toUrl;
        int i = 0;
        int size = 10;
        boolean hasMore;
        do {
            toUrl = StrUtil.format(VIDEO_LIST_PATH, itemId, size, size * i++);
            String jsonStr = Jsoup.connect(toUrl)
                .headers(headers())
                .ignoreContentType(true).execute().body();

            cn.hutool.json.JSONObject data = JSONUtil.parseObj(jsonStr);
            List<VideoInfo> collect = data.getJSONArray("aweme_list")
                .toList(JSONObject.class).parallelStream()
                .map(item -> {
                    VideoInfo itemResult = new VideoInfo();
                    String title = Optional.ofNullable(item.getJSONArray("cha_list"))
                        .flatMap(t -> t.toJavaList(JSONObject.class).stream().map(t2 -> t2.getString("cha_name"))
                            .collect(Collectors.toList()).stream().findFirst())
                        .orElse(null);

                    // 视频名称
                    String desc = item.getString("desc");
                    itemResult.setDesc(desc);
                    itemResult.setTitle(title);

                    // 关键词
                    List<String> keywords = item.getJSONArray("text_extra").toJavaList(JSONObject.class).stream()
                        .map(t -> t.getString("hashtag_name")).collect(Collectors.toList());
                    itemResult.setKeywords(keywords);

                    // 视频地址
                    Optional.ofNullable(item.getJSONObject("video"))
                        .map(i1 -> i1.getJSONObject("play_addr"))
                        .map(i1 -> i1.getJSONArray("url_list"))
                        .ifPresent(i1 -> itemResult.setUrl(i1.toJavaList(String.class).stream().findFirst().orElse(null)));

                    // 视频时长
                    itemResult.setDuration(item.getLong("duration"));
                    return itemResult;
                }).collect(Collectors.toList());

            result.addAll(collect);
            hasMore = data.getBool("has_more", false);
        } while (hasMore);
        return result;
    }


}
