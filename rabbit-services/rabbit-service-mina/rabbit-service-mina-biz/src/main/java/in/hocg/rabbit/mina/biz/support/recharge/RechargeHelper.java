package in.hocg.rabbit.mina.biz.support.recharge;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RechargeHelper {
    public static final String USER_ID = "531";
    private static final String API_KEY = "34cxZ8dULFaz7NkhCrpM2qbJSnA9QGyw";

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
        String signStr = params.keySet().stream().sorted()
            .reduce((a, b) -> StrUtil.format("{}={}&{}={}", a, params.get(a), b, params.get(b)))
            .map(s -> s + "&apikey=" + RechargeHelper.API_KEY).orElseThrow();
        return SecureUtil.md5(signStr).toUpperCase();
    }
}
