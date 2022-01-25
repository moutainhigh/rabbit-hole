package in.hocg.rabbit.com.api.enums.message;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.MMS_MESSAGE_USER_REF_TYPE, description = "消息用户关联类型")
public enum MessageUserRefType implements DataDictEnum {
    SystemMessage("system_message", "系统消息"),
    PersonalMessage("personal_message", "私信消息"),
    NoticeMessage("notice_message", "订阅消息"),
    ;
    private final Serializable code;
    private final String name;

}
