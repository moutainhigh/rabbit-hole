package com.github.lotus.pay.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class AccessAppOrdinaryVo {
    private Long id;
    @ApiModelProperty("应用编号")
    private String encoding;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建ip")
    private String createdIp;
}