package com.github.lotus.chaos.biz.manager;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import in.hocg.boot.utils.ValidUtils;
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

    /**
     * 同步小米步数
     *
     * @param username username
     * @param password password
     * @param count    count
     */
    public void uploadMiStep(String username, String password, Integer count) {
        String url = StrUtil.format("https://api.5173kk.com/cloudApi/sport/mi/submit?mobile={}&password={}&count={}", username, password, count);
        HttpRequest request = HttpUtil.createPost(url);
        HttpResponse response = request.execute();

        JSONObject result = JSON.parseObject(response.body());
        String msg = result.getString("msg");
        Integer code = result.getInteger("code");
        if (code != 0) {
            ValidUtils.fail(msg);
        }
    }

}
