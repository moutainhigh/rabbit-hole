package in.hocg.rabbit.com.biz.message;

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
    TriggerSubscribeNotice("trigger_subscribe_notice", "触发订阅通知"),
    TriggerCommented("trigger_commented", "触发评论"),
    ;
    private final String code;
    private final String desc;
}
