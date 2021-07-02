package com.github.lotus.chaos.biz.manager;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.github.lotus.chaos.biz.support.I2ProxyHttpClient;
import com.github.lotus.chaos.biz.support.MiSupport;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LangManager {
    private final StringRedisTemplate redisTemplate;

    /**
     * 获取冈布奥每日密令
     *
     * @return
     */
    public String getGumballsGift() {
        HashMap<String, Object> formMap = Maps.newHashMap();
        formMap.put("type", "1");
        HttpRequest request = HttpUtil.createPost("http://wechat.leiting.com/weixin/gumballs/201610/gift/common/getGift.php")
            .form(formMap).setFollowRedirects(true);
        HttpResponse response = request.execute();
        return JSON.parseObject(response.body()).getJSONArray("message").getString(0);
    }

    /**
     * 同步小米步数
     *
     * @param username username
     * @param password password
     * @param count    count
     */
    public void uploadMiStep(String username, String password, Integer count) {
        I2ProxyHttpClient.proxy(new Function<HttpHost, String>() {
            @Override
            public String apply(HttpHost httpHost) {
                MiSupport.loginAndUpdate(username, password, count, httpHost);
                return "ok";
            }
        });
    }

    public Optional<HttpHost> getProxyIp() {
//        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String url = "http://api.520e.com.cn/api/ip/getIp";
        String body = null; //ops.get(url);
        if (Objects.isNull(body)) {
            body = HttpRequest.get(url).execute().body();
            Assert.notBlank(body);
        }
        final cn.hutool.json.JSONObject result = JSONUtil.parseObj(body);
        Assert.isTrue(result.getInt("code") == 0, result.getStr("msg"));
        cn.hutool.json.JSONObject data = result.getJSONObject("data");

//        ops.set(url, body, data.getLong("time") - 1000);
        return Optional.of(new HttpHost(data.getStr("ip"), data.getInt("port")));
    }

}
