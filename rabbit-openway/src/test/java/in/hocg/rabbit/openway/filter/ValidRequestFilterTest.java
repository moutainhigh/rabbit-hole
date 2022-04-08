package in.hocg.rabbit.openway.filter;


import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import in.hocg.rabbit.openway.dto.RequestBody;
import in.hocg.rabbit.openway.utils.OpenwayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.HttpClient;

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
        ToRequestBody body = new ToRequestBody();
        body.setAppid("appid");
        String method = "E12453";
        body.setMethod(method);
        body.setSignType("md5");
        body.setTimestamp(LocalDateTime.now());
        body.setBizContent(new HashMap<>() {{
            put("name", "hocgin");
        }});
        String json = JSONUtil.toJsonStr(body);
        body.setSign(OpenwayUtils.getSign(json));

        String reqBody = JSONUtil.toJsonStr(body);
        String resp = HttpUtil.post("http://127.0.0.1:20010/", reqBody);

        Pair<String, String> result = getBody(resp, method);
        System.out.println("Requ::" + reqBody);
        System.out.println("Resp::" + resp);
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
