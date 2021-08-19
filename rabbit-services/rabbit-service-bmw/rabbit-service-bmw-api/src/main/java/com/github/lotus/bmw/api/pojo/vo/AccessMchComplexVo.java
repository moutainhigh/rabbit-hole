package com.github.lotus.bmw.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class AccessMchComplexVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("商户编码")
    private String encoding;
    @ApiModelProperty("商户标题")
    private String title;
}
