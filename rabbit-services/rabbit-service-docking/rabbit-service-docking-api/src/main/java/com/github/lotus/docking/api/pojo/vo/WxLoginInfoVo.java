package com.github.lotus.docking.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel("微信登陆状态")
public class WxLoginInfoVo {
    @ApiModelProperty("账号")
    private String openid;
    @ApiModelProperty("当前登陆状态")
    private WxLoginStatus status;

    @Getter
    @RequiredArgsConstructor
    public enum WxLoginStatus {
        Success("success", "成功"),
        Processing("processing", "进行中"),
        Fail("fail", "失败");
        private final Serializable code;
        private final String name;
    }
}
