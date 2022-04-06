package in.hocg.rabbit.mina.biz.support.recharge;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RechargeHelper {
    public static final String USER_ID = "535";
    private static final String API_KEY = "84598C8B2F5BF76D5647C5E676A1751F";

    /**
     * //签名参数只是示例，并非真实提交数据
     * $param = ["参数名称"=>"参数值",...];
     * //字典排序
     * ksort($param);
     * //拼接签名串
     * $sign_str = http_build_query($param) . '&apikey=aaaaaaaaaaaaaaaaaaa';
     * //签名
     * $sign = strtoupper(md5(urldecode($sign_str)));
     * $param['sign'] = $sign;
     * $httpdata = $param;
     */
    public static String getSign(Map<String, Object> params) {
        String signStr = params.keySet().stream().sorted(Comparator.comparing(o -> o))
            .filter(StrUtil::isNotBlank)
            .filter(s -> !"sign".equals(s))
            .map(s -> StrUtil.format("{}={}", s, ObjectUtil.defaultIfNull(params.get(s), "")))
            .reduce((a, b) -> StrUtil.format("{}&{}", a, b))
            .map(s -> s + "&apikey=" + RechargeHelper.API_KEY).orElseThrow();
        return SecureUtil.md5(signStr).toUpperCase();
    }
}
