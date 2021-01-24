package com.github.lotus.ums.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ApiComplexVo {
    private Long id;
    @ApiModelProperty("接口编码")
    private String encoding;
    @ApiModelProperty("接口名称")
    private String title;
    @ApiModelProperty("接口描述")
    private String remark;
    @ApiModelProperty("请求方式: 比如: GET,POST 或者 *")
    private String requestMethod;
    @ApiModelProperty("服务接口前缀, 比如: /chaos 或者 *")
    private String servicePrefix;
    @ApiModelProperty("请求URI, 比如: /xx/{id}  或者 /**")
    private String requestUri;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty("优先级, 越大优先级越低")
    private Integer priority;
    @ApiModelProperty("是否保留权限")
    private Boolean isPersist;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
}
