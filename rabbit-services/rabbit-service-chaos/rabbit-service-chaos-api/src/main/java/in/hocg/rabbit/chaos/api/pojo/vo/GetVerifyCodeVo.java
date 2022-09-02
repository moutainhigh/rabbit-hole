package in.hocg.rabbit.chaos.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(description = "获取短信验证码")
public class GetVerifyCodeVo implements Serializable {
    @ApiModelProperty("验证码序号")
    private String serialNo;
    @ApiModelProperty("剩余过期时间(秒)")
    private Long duration;
}
