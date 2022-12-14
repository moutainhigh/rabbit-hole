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
public class MinaGameCreateRoomRo {
    @NotNull(message = "房间号错误")
    @ApiModelProperty("房间号")
    private String encoding;
    @NotNull(message = "房间名错误")
    @ApiModelProperty("房间名")
    private String title;
    @ApiModelProperty("logo url")
    private String logoUrl;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("游戏")
    private Long game;

    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
