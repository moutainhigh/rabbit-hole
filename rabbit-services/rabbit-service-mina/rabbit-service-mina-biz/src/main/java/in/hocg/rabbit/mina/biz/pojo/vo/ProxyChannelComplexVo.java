package in.hocg.rabbit.mina.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/11/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ProxyChannelComplexVo {
    @ApiModelProperty("隧道标识(唯一)")
    private String channelId;
    @ApiModelProperty("拥有人")
    private Long userId;
    @ChaosNamed(idFor = "userId", type = ChaosNamedType.Userid2Nickname)
    private String userName;
    @ApiModelProperty("隧道类型")
    private String type;
    @ApiModelProperty("本地端口")
    private Integer localPort;
    @ApiModelProperty("本地IP")
    private String localIp;
    @ApiModelProperty("域名前缀: hocgin")
    private String prefix;
    @ApiModelProperty("域名后缀: forward.hocgin.top")
    private String suffix;
    @ApiModelProperty("开启状态")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;
}
