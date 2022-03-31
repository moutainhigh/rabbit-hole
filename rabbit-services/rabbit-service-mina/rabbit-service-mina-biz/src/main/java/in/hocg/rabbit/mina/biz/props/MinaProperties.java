package in.hocg.rabbit.mina.biz.props;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/3/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = MinaProperties.PREFIX)
public class MinaProperties {
    public static final String PREFIX = "rabbit.mina";

    @ApiModelProperty("存储路径")
    private String diskPath;
}
