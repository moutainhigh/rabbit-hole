package com.github.lotus.docking.biz.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/3/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class UserInfoDto {
    @ApiModelProperty(value = "微信小程序会话标记", required = true)
    private String code;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("昵称")
    private String nickName;
}
