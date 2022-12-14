package in.hocg.rabbit.mina.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedServiceApi;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
@ApiModel(description = "小游戏")
public class GameCardComplexVo {
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
    private List<String> tags = Collections.emptyList();
    @ApiModelProperty("排序,默认:1000")
    private Integer priority;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty("简介")
    private String bodyStr;
    @ApiModelProperty("评分")
    private BigDecimal score;
    @ApiModelProperty("热度")
    private Long heat;
    @ApiModelProperty("游戏类型")
    private String gameType;
    @ApiModelProperty("主图")
    private String mainViewUrl;
    @ApiModelProperty("展示图表")
    private List<String> viewUrls = Collections.emptyList();

    @ApiModelProperty("创建者")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
}
