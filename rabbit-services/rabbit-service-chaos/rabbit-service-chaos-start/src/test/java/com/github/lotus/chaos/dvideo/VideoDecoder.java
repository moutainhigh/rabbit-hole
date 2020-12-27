package com.github.lotus.chaos.dvideo;

import com.github.lotus.chaos.dvideo.common.IPs;
import com.github.lotus.chaos.dvideo.constant.UserAgent;
import com.google.common.collect.Maps;

import java.util.HashMap;
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

    String decoding(String url) throws Exception;

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
}
