package in.hocg.rabbit.openway.filter;


import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class ValidRequestFilterTest {
    String APP_ID = "--使用你的APPID--";
    String SECRET_KEY = "--使用你的 SECRET KEY--";
    String signType = "md5";

    @Test
    public void queryProduct() {
        System.out.println("查询产品------------------");
        ToRequestBody body = new ToRequestBody();
        body.setAppid(APP_ID);
        String method = "ER0003";
        body.setMethod(method);
        body.setSignType(signType.toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(getSign(json, signType, SECRET_KEY));

        String reqBody = JSONUtil.toJsonStr(body);
        String resp = HttpUtil.post("https://openapi.hocgin.top", reqBody);
        System.out.println("Resp::" + resp);
        Pair<String, String> result = getBody(resp, method);
        System.out.println("Requ::" + reqBody);
        System.out.println("Body::" + result.getLeft());
        System.out.println("Sign::" + result.getRight());
    }

    @Test
    public void queryRechargeResult() {
        System.out.println("查询充值结果------------------");
        ToRequestBody body = new ToRequestBody();
        body.setAppid(APP_ID);
        String method = "ER0002";
        body.setMethod(method);
        body.setSignType(signType.toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
            put("outOrderNo", "--填写单号--");
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(getSign(json, signType, SECRET_KEY));

        String reqBody = JSONUtil.toJsonStr(body);
        String resp = HttpUtil.post("https://openapi.hocgin.top", reqBody);
        System.out.println("Resp::" + resp);
        Pair<String, String> result = getBody(resp, method);
        System.out.println("Requ::" + reqBody);
        System.out.println("Body::" + result.getLeft());
        System.out.println("Sign::" + result.getRight());
    }

    @Test
    public void recharge() {
        ToRequestBody body = new ToRequestBody();
        body.setAppid(APP_ID);
        String method = "ER0001";
        body.setMethod(method);
        body.setSignType(signType.toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
            put("outOrderNo", "TEST_" + System.currentTimeMillis());
            put("productId", "--填写产品ID--");
            put("account", "--填写号码--");
            put("maxCostAmt", null);
            put("notifyUrl", null);
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(getSign(json, signType, SECRET_KEY));

        String reqBody = JSONUtil.toJsonStr(body);
        String resp = HttpUtil.post("https://openapi.hocgin.top", reqBody);
        System.out.println("Resp::" + resp);
        Pair<String, String> result = getBody(resp, method);
        System.out.println("Requ::" + reqBody);
        System.out.println("Body::" + result.getLeft());
        System.out.println("Sign::" + result.getRight());
    }


    // 解析消息
    public static Pair<String, String> getBody(String body, String method) {
        Map<String, String> result = JSONUtil.toBean(body, new TypeReference<>() {
        }, true);
        String respBody = result.get(method);
        String searchStr = "\"" + method + "\":";
        int startIndex = StrUtil.ordinalIndexOf(body, searchStr, 1) + searchStr.length();
        return Pair.of(StrUtil.subWithLength(body, startIndex, respBody.length()), result.get("sign"));
    }

    public String getSign(String requestBody, String signType, String secretKey) {
        JSONObject params = JSONUtil.toBean(requestBody, JSONObject.class);
        String signStr = params.keySet().stream().sorted(Comparator.comparing(o -> o))
            .filter(StrUtil::isNotBlank)
            .filter(s -> !"sign".equals(s))
            .map(s -> StrUtil.format("{}={}", s, ObjectUtil.defaultIfNull(params.get(s), "")))
            .reduce((a, b) -> StrUtil.format("{}&{}", a, b))
            .map(s -> s + "&secretKey=" + secretKey)
            .orElseThrow();
        return getSignStr(signStr);
    }

    public String getSignStr(String str) {
        return SecureUtil.md5(str).toLowerCase();
    }

}
