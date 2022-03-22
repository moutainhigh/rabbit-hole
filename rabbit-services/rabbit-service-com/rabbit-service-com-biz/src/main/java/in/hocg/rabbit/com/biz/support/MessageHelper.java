package in.hocg.rabbit.com.biz.support;

import in.hocg.rabbit.com.api.enums.message.MessageType;
import in.hocg.rabbit.com.api.enums.message.NoticeMessageEventType;
import in.hocg.rabbit.com.biz.entity.NoticeMessage;
import in.hocg.rabbit.com.biz.entity.PersonalMessage;
import in.hocg.rabbit.com.biz.entity.SystemMessage;
import in.hocg.rabbit.common.utils.Rules;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Created by hocgin on 2022/3/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MessageHelper {

    /**
     * 生成通知的文本
     *
     * @param eventType
     * @param context
     * @return
     */
    public String getSendNoticeMessageContent(NoticeMessageEventType eventType, Map<String, Object> context) {
        return "您有一条新的通知消息，请注意查收！";
    }

    /**
     * 根据消息实体获取消息类型
     *
     * @param clazz
     * @return
     */
    public MessageType entityToMessageType(Class<?> clazz) {
        return (MessageType) Rules.create()
            .rule(NoticeMessage.class, Rules.Supplier(() -> MessageType.NoticeMessage))
            .rule(SystemMessage.class, Rules.Supplier(() -> MessageType.SystemMessage))
            .rule(PersonalMessage.class, Rules.Supplier(() -> MessageType.PersonalMessage))
            .of(clazz).orElseThrow(UnsupportedOperationException::new);
    }
}
