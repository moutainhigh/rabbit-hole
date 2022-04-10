package in.hocg.rabbit.owp.api.pojo.vo;

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
public class ApiRouterVo implements Serializable {
    @ApiModelProperty("接口编号")
    private String encoding;
    @ApiModelProperty("接口名称")
    private String title;
    @ApiModelProperty("映射服务名/域名")
    private String mapUri;
    @ApiModelProperty("映射接口地址")
    private String mapPath;
}
