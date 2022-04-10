package in.hocg.rabbit.owp.api.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@InjectNamed
public class DevAppVo implements Serializable {
    @ApiModelProperty("开发者")
    private Long userId;
    @ApiModelProperty("开发者")
    private String username;
    @ApiModelProperty("应用密钥")
    private String secretKey;
    @ApiModelProperty("是否过期")
    private Boolean expired;
}
