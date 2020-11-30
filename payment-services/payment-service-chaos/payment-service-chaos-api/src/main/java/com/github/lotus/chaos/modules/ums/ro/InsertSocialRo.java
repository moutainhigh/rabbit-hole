package com.github.lotus.chaos.modules.ums.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class InsertSocialRo {
    @NotNull
    @ApiModelProperty("账号")
    private Long accountId;
    @NotNull
    @ApiModelProperty("社交类型")
    private String socialType;
    @NotNull
    @ApiModelProperty("社交账号")
    private String socialId;
}
