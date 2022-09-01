package in.hocg.rabbit.chaos.api.named;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by hocgin on 2021/12/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public final class ChaosDataDictKeys {
    @ApiModelProperty("引用类型")
    public final static String REF_TYPE = "RefType";
    @ApiModelProperty("获取验证码操作类型")
    public final static String GET_VERIFY_CODE_OPT_TYPE = "GetVerifyCodeOptType";
    @ApiModelProperty("获取验证码设备类型")
    public final static String GET_VERIFY_CODE_DEVICE_TYPE = "GetVerifyCodeDeviceType";
}
