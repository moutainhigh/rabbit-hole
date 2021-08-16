package com.github.lotus.bmw.biz.support.payment.pojo.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/5/31.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class GoPayResponse {
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

        public static WxJSAPI create(String timestamp, String nonceStr, String packageName, String signType, String paySign) {
            return new WxJSAPI(timestamp, nonceStr, "prepay_id=" + packageName, signType, paySign);
        }
    }
}
