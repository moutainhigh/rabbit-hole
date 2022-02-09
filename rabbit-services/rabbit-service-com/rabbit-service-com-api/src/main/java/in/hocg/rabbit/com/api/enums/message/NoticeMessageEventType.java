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
@UseDataDictKey(value = ComDataDictKeys.MMS_NOTICE_MESSAGE_EVENT_TYPE, description = "通知消息事件类型")
public enum NoticeMessageEventType implements DataDictEnum {
    CommentBeEvaluated("comment_be_evaluated", "评论被评论"),
    ;
    private final Serializable code;
    private final String name;

}
