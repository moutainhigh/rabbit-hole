package com.github.lotus.docking.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2020/12/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
public class GetMaUserTokenRo {
    @NotBlank(message = "参数错误")
    private String code;
    @NotBlank(message = "参数错误")
    private String signature;
    @NotBlank(message = "参数错误")
    private String rawData;
    @NotBlank(message = "参数错误")
    private String encryptedData;
    @NotBlank(message = "参数错误")
    private String iv;
}
