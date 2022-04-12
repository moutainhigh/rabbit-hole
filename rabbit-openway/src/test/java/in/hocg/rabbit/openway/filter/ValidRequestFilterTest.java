package in.hocg.rabbit.openway.filter;


import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.basic.data.RequestBody;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import in.hocg.rabbit.openway.utils.OpenwayUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class ValidRequestFilterTest {

    public static void main(String[] args) {
        System.out.println("正确请求------------------");
        RequestBody.SignType signType = RequestBody.SignType.MD5;
        ToRequestBody body = new ToRequestBody();
        body.setAppid("test");
        String method = "E12453";
        body.setMethod(method);
        body.setSignType(signType.getType().toLowerCase());
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
            put("name", "hocgin");
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(OpenwayUtils.getSign(json, signType, "test"));

        String reqBody = JSONUtil.toJsonStr(body);
        String resp = HttpUtil.post("http://127.0.0.1:20010", reqBody);

        System.out.println("Resp::" + resp);
        Pair<String, String> result = getBody(resp, method);
        System.out.println("Requ::" + reqBody);
        System.out.println("Body::" + result.getLeft());
        System.out.println("Sign::" + result.getRight());


//        System.out.println("错误请求------------------");
//        body.setAppid(null);
//        reqBody = JSONUtil.toJsonStr(body);
//        resp = HttpUtil.post("http://127.0.0.1:20010", reqBody);
//        System.out.println("Resp::" + resp);
//
//        System.out.println("不支持请求------------------");
//
//        resp = HttpUtil.get("http://127.0.0.1:20010");
//        System.out.println("Resp::" + resp);
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
