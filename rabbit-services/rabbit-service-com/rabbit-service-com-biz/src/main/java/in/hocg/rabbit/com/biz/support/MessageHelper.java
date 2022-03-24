package in.hocg.rabbit.com.biz.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
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
     * 消息标题
     *
     * @return
     */
    public String getMessageTitle(Object message) {
        if (message instanceof NoticeMessage) {
            return StrUtil.format("您订阅的{},有一条新的{}", ((NoticeMessage) message).getRefType(), ((NoticeMessage) message).getEventType());
        } else if (message instanceof SystemMessage) {
            return "系统公告";
        } else if (message instanceof PersonalMessage) {
            return "私信";
        }
        return "-";
    }

    /**
     * 消息描述
     *
     * @param content
     * @return
     */
    public String getMessageDescription(String content) {
        return StrUtil.sub(HtmlUtil.removeHtmlTag(content), 0, 300);
    }


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
