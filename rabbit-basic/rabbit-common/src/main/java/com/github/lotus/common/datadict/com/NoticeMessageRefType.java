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
public enum NoticeMessageRefType implements DataDictEnum {
    Comment("comment", "评论"),
//    Comment("comment", "评论")
    ;
    private final Serializable code;
    private final String name;

    public final static String KEY = "mms_notice_message_ref_type";
}
