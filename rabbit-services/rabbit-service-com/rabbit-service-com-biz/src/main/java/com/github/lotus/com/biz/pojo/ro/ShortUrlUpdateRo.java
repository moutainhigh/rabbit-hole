package com.github.lotus.com.biz.pojo.ro;


import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/4/4.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class ShortUrlUpdateRo {
    @NotBlank(message = "请输入链接")
    @ApiModelProperty("原链")
    private String originalUrl;
    @NotNull(message = "请选择启用状态")
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty(value = "用户", hidden = true)
    private Long userId;
}
