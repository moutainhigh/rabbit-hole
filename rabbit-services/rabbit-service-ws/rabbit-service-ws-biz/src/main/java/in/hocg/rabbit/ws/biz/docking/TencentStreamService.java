package in.hocg.rabbit.ws.biz.docking;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by hocgin on 2022/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class TencentStreamService {
    private final String PUSH_DOMAIN = "160107.livepush.myqcloud.com";
    private final String APP_NAME = "live";
    private final String SECRET_KEY = "8e2fb8fc8ba8cc66dfda54808c8ab680";

    /**
     * 获取推流地址
     *
     * @param streamName
     * @return
     */
    public String getPushUrl(String streamName) {
        String txTime = Long.toHexString(getExpired()).toUpperCase();
        String secret = MD5.create().digestHex(SECRET_KEY + streamName + txTime);
        return StrUtil.format("rtmp://{}/{}/{}?txSecret={}&txTime={}", PUSH_DOMAIN, APP_NAME, streamName, secret, txTime);
    }

    /**
     * 获取播放地址
     *
     * @return
     */
    public String getPlayUrl(String streamName) {
        String txTime = Long.toHexString(getExpired()).toUpperCase();
        String secret = MD5.create().digestHex(SECRET_KEY + streamName + txTime);
        String prefix = "rtmp";
        String suffix = "";
        return StrUtil.format("{}://{}/{}/{}{}?txSecret={}&txTime={}", prefix, PUSH_DOMAIN, APP_NAME, streamName, suffix, secret, txTime);
    }

    private Long getExpired() {
        return DateUtil.offsetDay(new Date(), 2).getTime() / 1000;
    }

    public static void main(String[] args) {
        String test = new TencentStreamService().getPushUrl("test");
        System.out.println(test);
        test = new TencentStreamService().getPlayUrl("test");
        System.out.println(test);
    }
}
