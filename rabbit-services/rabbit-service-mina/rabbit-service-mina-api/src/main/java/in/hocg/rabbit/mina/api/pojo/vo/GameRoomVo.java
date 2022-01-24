package in.hocg.rabbit.mina.api.pojo.vo;

import in.hocg.boot.named.annotation.Named;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2022/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class GameRoomVo {
    @ApiModelProperty("玩家")
    private List<PlayerUser> players = Collections.emptyList();

    @Data
    @Accessors(chain = true)
    public static class PlayerUser {
        @ApiModelProperty("玩家")
        private Long userId;
        @ApiModelProperty("玩家标记")
        private String userFlag;
        @ApiModelProperty("信号标记")
        private String signalData;
    }
}
