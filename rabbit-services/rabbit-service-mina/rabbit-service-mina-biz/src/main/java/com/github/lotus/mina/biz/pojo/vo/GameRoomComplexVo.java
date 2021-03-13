package com.github.lotus.mina.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/3/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class GameRoomComplexVo {
    private Long id;
    @ApiModelProperty("房间号")
    private String encoding;
    @ApiModelProperty("房间名")
    private String title;
    @ApiModelProperty("房间类型")
    private String type;
    @ApiModelProperty("房间密码")
    private String password;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @ApiModelProperty("玩家")
    private List<PlayerUser> players = Collections.emptyList();

    @Data
    @ApiModel
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
