package in.hocg.rabbit.mina.biz.support;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2022/3/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class YouTubeHelper {
    public static String getRedirectUri(String clientId) {
        String hostname = SpringContext.getBootConfig().getHostname();
        return StrUtil.format("{}/mina/youtube/{}/callback", hostname, clientId);
    }

    public String asChannelFlag(Long channelId) {
        return StrUtil.format("y2b_channel:{}", channelId);
    }
}
