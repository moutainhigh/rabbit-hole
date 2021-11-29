package in.hocg.rabbit.mina.biz.support.channel;

import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2021/11/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ChannelHelper {

    /**
     * 获取代理隧道的域名
     *
     * @param channel 渠道
     * @return 域名
     */
    public String getDomain(ProxyChannel channel) {
        String prefix = channel.getPrefix();
        String suffix = channel.getSuffix();
        return StrUtil.format("{}.{}", prefix, suffix);
    }
}
