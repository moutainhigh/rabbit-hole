package com.github.lotus.com.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ProjectComplexVo {
    private Long id;
    @ApiModelProperty("编码")
    private String projectSn;
    @ApiModelProperty("图章地址")
    private String logoUrl;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
}
