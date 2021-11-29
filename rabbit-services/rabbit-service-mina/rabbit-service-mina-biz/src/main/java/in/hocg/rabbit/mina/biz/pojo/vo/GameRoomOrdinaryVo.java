package in.hocg.rabbit.mina.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.ChaosNamedApi;
import in.hocg.rabbit.chaos.api.NamedType;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/3/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GameRoomOrdinaryVo {
    private Long id;
    @ApiModelProperty("房间号")
    private String encoding;
    @ApiModelProperty("房间名")
    private String title;
    @ApiModelProperty("房间类型")
    private String type;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
}
