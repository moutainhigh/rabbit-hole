package com.github.lotus.bmw.biz.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by hocgin on 2021/1/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
@ConditionalOnProperty(prefix = PaymentProperties.PREFIX, name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(PaymentProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentConfiguration {
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }
}
