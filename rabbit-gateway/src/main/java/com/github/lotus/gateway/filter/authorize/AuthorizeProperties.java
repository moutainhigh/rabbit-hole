package com.github.lotus.gateway.filter.authorize;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by hocgin on 2021/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
@Configuration
@ConfigurationProperties(prefix = AuthorizeProperties.PREFIX)
public class AuthorizeProperties {
    public static final String PREFIX = "gateway.authorize";
    /**
     * 忽略的IP列表
     */
    private List<String> ignoreIps = Lists.newArrayList();

    /**
     * 忽略的URI列表
     */
    private List<String> ignoreUris = Lists.newArrayList();
}
