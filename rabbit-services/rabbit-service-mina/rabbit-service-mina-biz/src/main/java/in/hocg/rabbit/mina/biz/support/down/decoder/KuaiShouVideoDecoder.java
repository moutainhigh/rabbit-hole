package in.hocg.rabbit.mina.biz.support.down.decoder;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import in.hocg.rabbit.mina.biz.support.down.VideoDecoder;
import in.hocg.rabbit.mina.biz.support.down.common.IPs;
import in.hocg.rabbit.mina.biz.support.down.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class KuaiShouVideoDecoder implements VideoDecoder {

    @Override
    public String decoding(String url) throws Exception {
        String redirectUrl = HttpUtil.createGet(filterUrl(url))
            .addHeaders(headers())
            .execute()
            .header("Location");
        String body = HttpUtil.createGet(redirectUrl)
            .addHeaders(headers())
            .execute().body();
        Document doc = Jsoup.parse(body);
        Elements videoElement = doc.select("script[type=text/javascript]");
        String videoInfo = videoElement.get(3).data().replaceAll("window.pageData= ", "");
        JSONObject json = JSONObject.parseObject(videoInfo);
        String title = json.getJSONObject("video").getString("caption");
        String videoUrl = json.getJSONObject("video").getString("srcNoMark");
        videoUrl = videoUrl.substring(0, videoUrl.indexOf("?"));
        log.info("视频名称: [{}], 无水印链接: [{}]", title, videoUrl);

        return videoUrl;
    }

    public static String getd(String url) {
        HashMap<String, String> headers = MapUtil.newHashMap();
        headers.put("X-FORWARDED-FOR", IPs.getRandomIp());
        headers.put("CLIENT-IP", IPs.getRandomIp());
        headers.put("User-Agent", UserAgent.ANDROID_USER_AGENT);
        String redirectUrl = HttpUtil.createGet(url).addHeaders(headers).execute().header("Location");
        Map<String, String> decodeParamMap = HttpUtil.decodeParamMap(redirectUrl, Charset.defaultCharset());
        String photoId = decodeParamMap.get("photoId");

        String param = "client_key=56c3713c&photoIds=" + photoId;

        String replace = StrUtil.replace(param, "&", "") + new String(new byte[]{50, 51, 99, 97, 97, 98, 48, 48, 51, 53, 54, 99});
        String sig = SecureUtil.md5().digestHex(replace);
        HttpResponse execute = HttpUtil.createPost("https://api.gifshow.com/rest/n/photo/info?" + param + "&sig=" + sig)
            .contentType("application/x-www-form-urlencoded").contentType("kwai-android").addHeaders(headers)
            .execute();
        String body = execute.body();
        log.debug(body);
        JSON res = JSONUtil.parse(body);
        String title = JSONUtil.getByPath(res, "photos.0.caption").toString();
        String playAddr = JSONUtil.getByPath(res, "photos.0.main_mv_url").toString();
        String cover = JSONUtil.getByPath(res, "photos.0.thumbnail_url").toString();
        String author = JSONUtil.getByPath(res, "photos.0.user_name").toString();
        String avatar = JSONUtil.getByPath(res, "photos.0.headurl").toString();
        return playAddr;
    }

    public static void main(String[] args) {
        String url = getd("http://v.kuaishou.com/s/mhn5haAq");
        System.out.printf(" " + url);
    }

}
