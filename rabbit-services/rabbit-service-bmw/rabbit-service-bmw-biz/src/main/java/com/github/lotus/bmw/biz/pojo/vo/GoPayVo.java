package com.github.lotus.bmw.biz.pojo.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import in.hocg.boot.utils.enums.DataDictEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class GoPayVo {
    private String payType;

    @ApiModelProperty("表单")
    private Form form;
    @ApiModelProperty("APP")
    private String app;
    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty("微信 - JSAPI")
    private WxJSAPI wxJsApi;
    @ApiModelProperty("微信 - Native")
    private String wxNative;

    @Data
    @RequiredArgsConstructor
    public static class Form {
        private final String method;
        private final String value;
    }

    @Data
    @RequiredArgsConstructor
    public static class WxJSAPI {
        private final String timestamp;
        private final String nonceStr;
        @JsonAlias("package")
        private final String packageName;
        private final String signType;
        private final String paySign;
    }
}
