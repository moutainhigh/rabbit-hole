package in.hocg.rabbit.mina.biz.support.down;

import in.hocg.rabbit.mina.biz.support.down.common.IPs;
import com.google.common.collect.Maps;
import in.hocg.rabbit.mina.biz.support.down.dto.MusicInfo;
import in.hocg.rabbit.mina.biz.support.down.dto.Top;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import in.hocg.rabbit.mina.biz.support.down.dto.WordInfo;
import org.jsoup.Connection;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface VideoDecoder {

    default Map<String, String> headers() {
        HashMap<String, String> headers = Maps.newHashMap();
        headers.put("X-FORWARDED-FOR", IPs.getRandomIp());
        headers.put("CLIENT-IP", IPs.getRandomIp());
        headers.put("User-Agent", UserAgent.ANDROID_USER_AGENT);
        return headers;
    }


    default String filterUrl(String url) {
        String regex = "https?://(\\w|-)+(\\.(\\w|-)+)+(/(\\w+(\\?(\\w+=(\\w|%|-)*(\\&\\w+=(\\w|%|-)*)*)?)?)?)+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (m.find()) {
            return url.substring(m.start(), m.end());
        }
        return "";
    }

    /**
     * 下载集合
     *
     * @param url
     * @return
     */
    default List<VideoInfo> listAweme(String url) {
        return Collections.emptyList();
    }

    VideoInfo aweme(String url);

    default List<Top<MusicInfo>> topMusic() {
        throw new UnsupportedOperationException();
    }

    default List<Top<WordInfo>> topWord() {
        throw new UnsupportedOperationException();
    }

    default List<Top<VideoInfo>> topAweme() {
        throw new UnsupportedOperationException();
    }

    default Connection jsoup(Connection connect) {
        return connect.headers(headers())
            .headers(headers())
            .ignoreHttpErrors(true)
            .validateTLSCertificates(false)
            .ignoreContentType(true);
    }

}
