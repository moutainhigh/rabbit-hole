package com.github.lotus.mina.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class GameCardOrdinaryVo {

    private Long id;
    @ApiModelProperty("名称")
    private String title;
    @ApiModelProperty("LOGO")
    private String logoUrl;
    @ApiModelProperty("ROM链接")
    private String gameUrl;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("标签(暂用;分隔)")
    private String tags;
    @ApiModelProperty("排序,默认:1000")
    private Integer priority;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
}
