package com.github.lotus.ums.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/7/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class GetLoginQrcodeVo {
    @ApiModelProperty("二维码链接")
    private String qrCodeUrl;
    @ApiModelProperty("标记")
    private String idFlag;
}
