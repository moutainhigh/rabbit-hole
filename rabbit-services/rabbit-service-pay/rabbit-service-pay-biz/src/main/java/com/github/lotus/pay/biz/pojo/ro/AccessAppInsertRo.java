package com.github.lotus.pay.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class AccessAppInsertRo {
    @NotNull(message = "应用编号错误")
    @ApiModelProperty("应用编号")
    private String encoding;
    @NotNull(message = "标题错误")
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty(hidden = true)
    private String clientIp;
}
