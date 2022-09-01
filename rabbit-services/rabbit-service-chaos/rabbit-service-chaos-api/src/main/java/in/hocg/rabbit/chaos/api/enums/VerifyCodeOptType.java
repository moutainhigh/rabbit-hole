package in.hocg.rabbit.chaos.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.chaos.api.named.ChaosDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ChaosDataDictKeys.GET_VERIFY_CODE_OPT_TYPE, description = "操作类型")
public enum VerifyCodeOptType implements DataDictEnum {
    Register("register", "注册账号"),
    Forgot("forgot", "忘记密码"),
    Other("other", "其他"),
    ;
    private final Serializable code;
    private final String name;
}
