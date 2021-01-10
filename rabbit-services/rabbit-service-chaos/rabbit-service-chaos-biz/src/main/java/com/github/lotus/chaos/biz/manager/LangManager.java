package com.github.lotus.chaos.biz.manager;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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
    private final RedisManager redisManager;

    /**
     * 获取冈布奥每日密令
     *
     * @return
     */
    public String getGumballsGift() {
        HashMap<String, Object> formMap = Maps.newHashMap();
        formMap.put("type", "1");
        HttpRequest request = HttpUtil.createPost("http://wechat.leiting.com/weixin/gumballs/201610/gift/common/getGift.php")
            .form(formMap);
        HttpResponse response = request.execute();
        return JSON.parseObject(response.body()).getJSONArray("message").getString(0);
    }

}
