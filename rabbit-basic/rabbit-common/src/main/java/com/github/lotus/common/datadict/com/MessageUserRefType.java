package com.github.lotus.common.datadict.com;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum MessageUserRefType implements DataDictEnum {
    SystemMessage("system_message", "系统消息"),
    PersonalMessage("personal_message", "私信消息"),
    NoticeMessage("notice_message", "订阅消息"),
    ;
    private final Serializable code;
    private final String name;

    public final static String KEY = "mms_message_user_ref_type";
}
