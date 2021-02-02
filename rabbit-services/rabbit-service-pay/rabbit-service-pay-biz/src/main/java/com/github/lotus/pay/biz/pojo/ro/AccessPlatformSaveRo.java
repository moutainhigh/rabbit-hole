package com.github.lotus.pay.biz.pojo.ro;

import com.github.lotus.common.datadict.bmw.PaymentPlatform;
import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class AccessPlatformSaveRo {
    @NotNull(message = "接入应用错误")
    @ApiModelProperty("接入应用")
    private Long accessAppId;
    @NotNull(message = "第三方支付平台错误")
    @EnumRange(enumClass = PaymentPlatform.class, message = "第三方支付平台错误")
    @ApiModelProperty("第三方支付平台")
    private String platform;
    @ApiModelProperty("支付宝配置")
    private AliPayConfig aliPayConfig;
    @ApiModelProperty("微信配置")
    private WxPayConfig wxPayConfig;

    @ApiModelProperty(hidden = true)
    private String clientIp;

    @Data
    @ApiModel
    public static class AliPayConfig {
        @ApiModelProperty("appid")
        private String appid;
        @ApiModelProperty("public key")
        private String publicKey;
        @ApiModelProperty("private key")
        private String privateKey;
        @ApiModelProperty("是否测试模式")
        private Boolean isDev;
    }

    @Data
    @ApiModel
    public static class WxPayConfig {
        @ApiModelProperty("appid")
        private String appid;
        @ApiModelProperty("mch_id")
        private String mchId;
        @ApiModelProperty("key")
        private String keyStr;
        @ApiModelProperty("cert file text")
        private String certStr;
    }
}
