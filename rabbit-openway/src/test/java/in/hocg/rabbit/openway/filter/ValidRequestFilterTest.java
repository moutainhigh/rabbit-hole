package in.hocg.rabbit.openway.filter;


import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.basic.data.RequestBody;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import in.hocg.rabbit.openway.utils.OpenwayUtils;
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

    public void queryProduct() {
        System.out.println("查询产品------------------");
        RequestBody.SignType signType = RequestBody.SignType.MD5;
        ToRequestBody body = new ToRequestBody();
        body.setAppid("all_test");
        String method = "ER0003";
        body.setMethod(method);
        body.setSignType(signType.getType().toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(OpenwayUtils.getSign(json, signType, "hocgin"));

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
        RequestBody.SignType signType = RequestBody.SignType.MD5;
        ToRequestBody body = new ToRequestBody();
        body.setAppid("all_test");
        String method = "ER0002";
        body.setMethod(method);
        body.setSignType(signType.getType().toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
            put("outOrderNo", "9gxtqehm1ivb2z5m");
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(OpenwayUtils.getSign(json, signType, "hocgin"));

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
        RequestBody.SignType signType = RequestBody.SignType.MD5;
        ToRequestBody body = new ToRequestBody();
        body.setAppid("all_test");
        String method = "ER0001";
        body.setMethod(method);
        body.setSignType(signType.getType().toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
            put("outOrderNo", "TEST_" + System.currentTimeMillis());
            put("productId", "5");
            put("account", "13600747016");
            put("maxCostAmt", null);
            put("notifyUrl", null);
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(OpenwayUtils.getSign(json, signType, "hocgin"));

        String reqBody = JSONUtil.toJsonStr(body);
        String resp = HttpUtil.post("https://openapi.hocgin.top", reqBody);
        System.out.println("Resp::" + resp);
        Pair<String, String> result = getBody(resp, method);
        System.out.println("Requ::" + reqBody);
        System.out.println("Body::" + result.getLeft());
        System.out.println("Sign::" + result.getRight());
    }


    public static Pair<String, String> getBody(String body, String method) {
        Map<String, String> result = JSONUtil.toBean(body, new TypeReference<>() {
        }, true);
        String respBody = result.get(method);
        String searchStr = "\"" + method + "\":";
        int startIndex = StrUtil.ordinalIndexOf(body, searchStr, 1) + searchStr.length();
        return Pair.of(StrUtil.subWithLength(body, startIndex, respBody.length()), result.get(OpenwayContants.SIGN));
    }

}
