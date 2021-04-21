package com.github.lotus.com.biz.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum MessageTopic {
    NoticeMessage("notice_message", "通知消息");
    private final String code;
    private final String desc;
}
