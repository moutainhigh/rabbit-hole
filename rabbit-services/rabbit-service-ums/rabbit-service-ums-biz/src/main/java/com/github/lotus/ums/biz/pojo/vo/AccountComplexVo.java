package com.github.lotus.ums.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/12/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class AccountComplexVo {
    private Long id;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("用户名")
    private String username;
}
