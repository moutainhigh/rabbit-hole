package in.hocg.rabbit.com.biz.basic;

import cn.hutool.core.lang.Snowflake;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hocgin on 2022/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
public class ComStarter {

    @Bean
    @ConditionalOnMissingBean
    public Snowflake snowflake() {
        return new Snowflake(1, 1);
    }
}
