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
public enum NoticeMessageEventType implements DataDictEnum {
    CommentBeEvaluated("comment_be_evaluated", "被评论");
    private final Serializable code;
    private final String name;

    public final static String KEY = "mms_notice_message_event_type";
}
