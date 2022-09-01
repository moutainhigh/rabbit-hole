package in.hocg.rabbit.chaos.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.chaos.api.named.ChaosDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ChaosDataDictKeys.GET_VERIFY_CODE_OPT_TYPE, description = "接收设备类型")
public enum VerifyCodeDeviceType implements DataDictEnum {
    Phone("phone", "手机"),
    Email("email", "邮箱"),
    ;
    private final Serializable code;
    private final String name;
}
