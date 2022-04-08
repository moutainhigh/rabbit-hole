package in.hocg.rabbit.openway.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import lombok.experimental.UtilityClass;

import java.util.Comparator;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class OpenwayUtils {

    public String getSign(String requestBody) {
        JSONObject params = JSONUtil.toBean(requestBody, JSONObject.class);
        String signStr = params.keySet().stream().sorted(Comparator.comparing(o -> o))
            .filter(StrUtil::isNotBlank)
            .filter(s -> !"sign".equals(s))
            .map(s -> StrUtil.format("{}={}", s, ObjectUtil.defaultIfNull(params.get(s), "")))
            .reduce((a, b) -> StrUtil.format("{}&{}", a, b))
//            .map(s -> s + "&apikey=" + RechargeHelper.API_KEY)
            .orElseThrow();
        return OpenwayUtils.getSignStr(signStr);
    }

    public String getSignStr(String str) {
        return SecureUtil.md5(str).toLowerCase();
    }
}
