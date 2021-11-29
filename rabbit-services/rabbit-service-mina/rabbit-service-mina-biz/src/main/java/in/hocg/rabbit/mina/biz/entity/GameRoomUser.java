package in.hocg.rabbit.mina.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [小程序模块] 游戏房间用户表
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mina_game_room_user")
public class GameRoomUser extends AbstractEntity<GameRoomUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("房间")
    @TableField("room_id")
    private Long roomId;
    @ApiModelProperty("玩家")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("玩家标记(P1,P2)")
    @TableField("user_flag")
    private String userFlag;
    @ApiModelProperty("信号标记")
    @TableField("signal_data")
    private String signalData;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;



}
