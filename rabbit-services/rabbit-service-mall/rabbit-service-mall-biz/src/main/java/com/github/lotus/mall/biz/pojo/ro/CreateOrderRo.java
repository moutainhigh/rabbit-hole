package com.github.lotus.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateOrderRo extends CalcOrderRo {
    @NotNull(message = "收货人信息错误")
    @ApiModelProperty("收货人信息")
    private Receiver receiver;

    @ApiModelProperty("订单备注")
    private String remark;

    @Data
    public static class Receiver {
        @ApiModelProperty("收货人姓名")
        private String name;
        @ApiModelProperty("收货人电话")
        private String phone;
        @ApiModelProperty("收货人邮编")
        private String postCode;
        @ApiModelProperty("收货人省份")
        private String province;
        @ApiModelProperty("收货人城市")
        private String city;
        @ApiModelProperty("收货人区")
        private String region;
        @ApiModelProperty("详细地址")
        private String address;
        @ApiModelProperty("区域编码")
        private String adcode;
    }
}