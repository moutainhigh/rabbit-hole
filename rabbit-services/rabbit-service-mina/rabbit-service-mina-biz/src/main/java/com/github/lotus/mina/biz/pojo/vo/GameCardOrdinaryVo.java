package com.github.lotus.mina.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import in.hocg.boot.named.autoconfiguration.annotation.UseNamedService;
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

    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
}
