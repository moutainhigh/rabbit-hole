package in.hocg.rabbit.mina.biz.support.down.decoder;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import in.hocg.rabbit.mina.biz.support.down.VideoDecoder;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
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
    @SneakyThrows
    public VideoInfo item(String url) {
        VideoInfo result = new VideoInfo();

        Connection con = Jsoup.connect(filterUrl(url));
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String urlStr = resp.url().toString();
        String itemId = StrUtil.subBetween(urlStr, "/video/", "?");

        String videoUrl = StrUtil.format(VIDEO_PATH, itemId);
        String jsonStr = Jsoup.connect(videoUrl)
            .headers(headers())
            .ignoreContentType(true).execute().body();

        JSONObject json = JSONUtil.parseObj(jsonStr);
        JSONObject item = json.getJSONArray("item_list").getJSONObject(0);
        String videoAddress = item.getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
        String title = StrUtil.removeAll(item.getJSONObject("share_info").getStr("share_title"), "#");
        videoAddress = StrUtil.nullToEmpty(videoAddress).replaceAll("playwm", "play");

        result.setId(item.getStr("aweme_id"));
        result.setDuration(item.getLong("duration"));
        result.setDesc(item.getStr("desc"));
        result.setKeywords(item.get("text_extra", JSONArray.class).toList(JSONObject.class).stream()
            .map(i -> i.getStr("hashtag_name")).collect(Collectors.toList()));

        String noVideoUrl = HttpUtil.createGet(videoAddress)
            .addHeaders(headers())
            .execute().header("Location");

        return result.setTitle(title).setUrl(noVideoUrl);
    }

    @Override
    @SneakyThrows
    public List<VideoInfo> list(String url) {
        List<VideoInfo> result = Lists.newArrayList();

        Connection con = Jsoup.connect(filterUrl(url));
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String urlStr = resp.url().toString();
        String itemId = StrUtil.subBetween(urlStr, "/detail/", "/");

        String toUrl;
        int i = 0;
        int size = 20;
        boolean hasMore;
        do {
            toUrl = StrUtil.format(VIDEO_LIST_PATH, itemId, size, size * i++);
            String jsonStr = Jsoup.connect(toUrl)
                .headers(headers())
                .ignoreHttpErrors(true)
                .validateTLSCertificates(false)
                .ignoreContentType(true)
                .execute().body();

            JSONObject data = JSONUtil.parseObj(jsonStr);
            Optional.ofNullable(data.get("aweme_list", JSONArray.class)).ifPresent(o1 -> {
                List<VideoInfo> collect = o1.toList(JSONObject.class).stream()
                    .map(item -> {
                        VideoInfo itemResult = new VideoInfo();
                        itemResult.setId(item.getStr("item_id"));

                        String title = Optional.ofNullable(item.get("cha_list", JSONArray.class))
                            .flatMap(t -> t.toList(JSONObject.class).stream().map(t2 -> t2.getStr("cha_name"))
                                .collect(Collectors.toList()).stream().findFirst())
                            .orElse(null);

                        // 视频名称
                        String desc = item.getStr("desc");
                        itemResult.setDesc(desc);
                        itemResult.setTitle(title);

                        // 关键词
                        Optional.ofNullable(item.get("text_extra", JSONArray.class)).ifPresent(objects -> {
                            List<String> keywords = objects.toList(JSONObject.class).stream()
                                .map(t -> t.getStr("hashtag_name")).collect(Collectors.toList());
                            itemResult.setKeywords(keywords);
                        });

                        // 视频地址
                        Optional.ofNullable(item.getJSONObject("video"))
                            .map(i1 -> i1.getJSONObject("play_addr"))
                            .map(i1 -> i1.getJSONArray("url_list"))
                            .ifPresent(i1 -> itemResult.setUrl(i1.toList(String.class).stream().findFirst().orElse(null)));

                        // 视频时长
                        itemResult.setDuration(item.getLong("duration"));
                        return itemResult;
                    }).collect(Collectors.toList());
                result.addAll(collect);
            });
            hasMore = data.getBool("has_more", false);
        } while (hasMore);
        return result;
    }


}
