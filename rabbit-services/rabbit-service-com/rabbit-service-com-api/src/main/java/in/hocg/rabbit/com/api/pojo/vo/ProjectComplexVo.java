package in.hocg.rabbit.com.api.pojo.vo;

import in.hocg.rabbit.chaos.api.ChaosNamedApi;
import in.hocg.rabbit.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
import io.swagger.annotations.ApiModel;
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
@ApiModel
@InjectNamed
public class ProjectComplexVo {
    private Long id;
    @ApiModelProperty("编码")
    private String encoding;
    @ApiModelProperty("图章地址")
    private String logoUrl;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("当前版本号")
    private String version;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("配置")
    private String config;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ApiModelProperty("创建人")
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
}
