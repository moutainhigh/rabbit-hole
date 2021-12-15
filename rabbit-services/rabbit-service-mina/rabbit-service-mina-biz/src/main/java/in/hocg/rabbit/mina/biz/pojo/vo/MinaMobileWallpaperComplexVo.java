package in.hocg.rabbit.mina.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedServiceApi;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.Named;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class MinaMobileWallpaperComplexVo {
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String remark;
    @ApiModelProperty("color")
    private String color;
    @ApiModelProperty("默认")
    private String fullUrl;
    @ApiModelProperty("文件hash")
    private String fileHash;
    @ApiModelProperty("标签(暂用;分隔)")
    private String tags;
    @ApiModelProperty("排序,默认:1000")
    private Integer priority;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ApiModelProperty("创建人")
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;

    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
}
