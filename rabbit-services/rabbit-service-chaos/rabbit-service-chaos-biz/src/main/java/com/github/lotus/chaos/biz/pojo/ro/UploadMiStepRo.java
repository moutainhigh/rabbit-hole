package com.github.lotus.chaos.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class UploadMiStepRo {
    @NotBlank(message = "账号错误")
    private String username;
    @NotBlank(message = "密码错误")
    private String password;
    @Min(value = 0, message = "步数错误")
    @NotNull(message = "步数错误")
    private Integer count;
}
