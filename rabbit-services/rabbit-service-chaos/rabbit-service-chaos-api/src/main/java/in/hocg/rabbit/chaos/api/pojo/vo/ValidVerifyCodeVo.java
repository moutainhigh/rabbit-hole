package in.hocg.rabbit.chaos.api.pojo.vo;

import in.hocg.boot.utils.ValidUtils;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeDeviceType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class ValidVerifyCodeVo implements Serializable {
    private Boolean success;
    private String optType;
    private String toDevice;
    private String verifyCode;
    private String deviceType;

    public String getDeviceNoThrow(VerifyCodeDeviceType deviceType) {
        ValidUtils.isTrue(this.getSuccess(), "验证码错误");
        ValidUtils.isTrue(Objects.isNull(deviceType) || deviceType.anyMatch(this.getDeviceType()), "验证码错误");
        return this.getToDevice();
    }
}
