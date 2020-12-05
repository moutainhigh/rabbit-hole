package com.github.lotus.docking.biz.support.mp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2020/12/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(WxProperties.PREFIX)
public class WxProperties {
    public static final String PREFIX = "rabbit.wx";
    private String appid;
    private String secret;
    private String token;
}
