package in.hocg.rabbit.mina.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/3/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class JoinRoomRo {
    @NotNull(message = "房间号")
    @ApiModelProperty("房间号")
    private String roomCode;
    @ApiModelProperty("密码")
    private String password;
    @NotNull(message = "用户标记")
    @ApiModelProperty("用户标记")
    private String userFlag;
    @NotNull(message = "信号")
    @ApiModelProperty("信号")
    private String signalData;

    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
