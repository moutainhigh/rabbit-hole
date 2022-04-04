package in.hocg.rabbit.mina.biz.support.down.decoder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mina.biz.support.down.VideoDecoder;
import in.hocg.rabbit.mina.biz.support.down.dto.MusicInfo;
import in.hocg.rabbit.mina.biz.support.down.dto.Top;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import in.hocg.rabbit.mina.biz.support.down.dto.WordInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.Collections;
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

        result.setId(item.getStr("aweme_id"));
        result.setDuration(item.getLong("duration"));
        result.setDesc(item.getStr("desc"));
        result.setKeywords(item.get("text_extra", JSONArray.class).toList(JSONObject.class).stream()
            .map(i -> i.getStr("hashtag_name")).collect(Collectors.toList()));

        String noVideoUrl = toNoWatermarkUrl(videoAddress);
        return result.setTitle(title).setUrl(noVideoUrl);
    }

    private String toNoWatermarkUrl(String originUrl) {
        return HttpUtil.createGet(StrUtil.nullToEmpty(originUrl).replaceAll("playwm", "play"))
            .addHeaders(headers()).execute().header("Location");
    }

    @Override
    @SneakyThrows
    public List<Top<MusicInfo>> topMusic() {
        List<Top<MusicInfo>> result = Lists.newArrayList();
        String body = jsoup(Jsoup.connect("https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/music/"))
            .execute().body();

        JSONArray objects = JSONUtil.parseObj(body).get("music_list", JSONArray.class);
        if (CollUtil.isEmpty(objects)) {
            return result;
        }
        for (JSONObject data : objects.toList(JSONObject.class)) {
            Top<MusicInfo> resultItem = new Top<>();
            resultItem.setHotValue(data.getLong("hot_value"));

            MusicInfo item = new MusicInfo();
            JSONObject musicInfo = data.getJSONObject("music_info");
            item.setAuthor(musicInfo.getStr("author"));
            item.setPlayUrl(musicInfo.getByPath("$.play_url.url_list[0]", String.class));
            item.setCoverUrl(musicInfo.getByPath("$.cover_large.url_list[0]", String.class));
            item.setTitle(musicInfo.getStr("title"));
            item.setId(musicInfo.getStr("id"));
            item.setDuration(musicInfo.getLong("duration"));
            result.add(resultItem.setValue(item));
        }
        return result;
    }

    @Override
    @SneakyThrows
    public List<Top<WordInfo>> topWord() {
        List<Top<WordInfo>> result = Lists.newArrayList();
        String body = jsoup(Jsoup.connect("https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/word/"))
            .execute().body();

        JSONArray objects = JSONUtil.parseObj(body).get("word_list", JSONArray.class);
        if (CollUtil.isEmpty(objects)) {
            return result;
        }
        for (JSONObject data : objects.toList(JSONObject.class)) {
            Top<WordInfo> resultItem = new Top<>();
            resultItem.setHotValue(data.getLong("hot_value"));

            WordInfo item = new WordInfo();
            item.setWord(data.getStr("word"));
            result.add(resultItem.setValue(item));
        }

        return result;
    }

    @Override
    @SneakyThrows
    public List<Top<VideoInfo>> topAweme() {
        List<Top<VideoInfo>> result = Lists.newArrayList();
        String body = jsoup(Jsoup.connect("https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/aweme/"))
            .execute().body();

        JSONArray objects = JSONUtil.parseObj(body).get("aweme_list", JSONArray.class);
        if (CollUtil.isEmpty(objects)) {
            return result;
        }
        for (JSONObject data : objects.toList(JSONObject.class)) {
            Top<VideoInfo> resultItem = new Top<>();
            resultItem.setHotValue(data.getLong("hot_value"));

            VideoInfo item = new VideoInfo();
            JSONObject itemData = data.getJSONObject("aweme_info");
            item.setId(data.getByPath("$.aweme_info.aweme_id", String.class));
            String url = itemData.getByPath("$.video.play_addr.url_list[0]", String.class);
            item.setOriginalUrl(url);
            item.setUrl(toNoWatermarkUrl(url));
            item.setTitle(itemData.getByPath("$.share_info.share_title", String.class));
            item.setDuration(itemData.getLong("duration"));
            item.setDesc(itemData.getByPath("$.desc", String.class));

            List<String> tags = LangUtils.callIfNotNull(itemData.get("text_extra", JSONArray.class), i -> i.toList(JSONObject.class))
                .orElse(Collections.emptyList()).stream().map(j -> j.getStr("hashtag_name")).collect(Collectors.toList());
            item.setKeywords(tags);

            result.add(resultItem.setValue(item));
        }
        return result;
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
