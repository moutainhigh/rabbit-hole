package com.github.lotus.ums.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/6/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户组 - 用户信息")
public class UserGroupRefUserVo {
    @ApiModelProperty("用户")
    private Long userId;
    private String username;
    private String nickname;
    private String avatarUrl;
}
