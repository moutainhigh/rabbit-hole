package com.github.lotus.ums.api.pojo.ro;

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
    @ApiModelProperty("账号")
    private Long accountId;
    @ApiModelProperty("社交类型")
    private String socialType;
    @ApiModelProperty("社交账号")
    private String socialId;
}
