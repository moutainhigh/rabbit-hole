package com.github.lotus.chaos.module.lang.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class IpAddressVo {
    @ApiModelProperty("IP")
    private String ip;
    @ApiModelProperty("国家")
    private String nation;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("区域编码")
    private String adCode;
    @ApiModelProperty("名称")
    private String title;
}
