package in.hocg.rabbit.com.biz.convert;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.com.api.enums.message.MessageType;
import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.entity.NoticeMessage;
import in.hocg.rabbit.com.biz.entity.PersonalMessage;
import in.hocg.rabbit.com.biz.entity.SystemMessage;
import in.hocg.rabbit.com.biz.mapstruct.MessageUserRefMapping;
import in.hocg.rabbit.com.biz.mapstruct.NoticeMessageMapping;
import in.hocg.rabbit.com.biz.mapstruct.PersonalMessageMapping;
import in.hocg.rabbit.com.biz.mapstruct.SystemMessageMapping;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.NoticeMessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.PersonalMessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.SystemMessageComplexVo;
import in.hocg.rabbit.com.biz.service.NoticeMessageService;
import in.hocg.rabbit.com.biz.service.PersonalMessageService;
import in.hocg.rabbit.com.biz.service.SystemMessageService;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.common.utils.Rules;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/2/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageConvert {
    private final MessageUserRefMapping messageUserRefMapping;
    private final SystemMessageMapping systemMessageMapping;
    private final NoticeMessageMapping noticeMessageMapping;
    private final PersonalMessageMapping personalMessageMapping;
    private final NoticeMessageService noticeMessageService;
    private final PersonalMessageService personalMessageService;
    private final SystemMessageService systemMessageService;

    public PersonalMessageComplexVo asPersonalMessageComplexVo(PersonalMessage entity) {
        return personalMessageMapping.asComplex(entity);
    }

    public SystemMessageComplexVo asSystemMessageComplexVo(SystemMessage entity) {
        return systemMessageMapping.asComplex(entity);
    }

    public NoticeMessageComplexVo asNoticeMessageComplexVo(NoticeMessage entity) {
        Long refId = entity.getRefId();
        NoticeMessageComplexVo result = noticeMessageMapping.asComplex(entity);
        NoticeMessageComplexVo.RefObject refObject = new NoticeMessageComplexVo.RefObject();
        refObject.setId(refId);

        Rules.create()
            .rule(RefType.Comment, Rules.Runnable(() -> refObject.setTitle("评论")))
            .of(ICode.ofThrow(entity.getRefType(), RefType.class));
        result.setRefObject(refObject);
        return result;
    }


    public MessageComplexVo asMessageComplexVo(MessageUserRef entity) {
        Long refId = entity.getMessageId();
        MessageType refType = ICode.ofThrow(entity.getMessageType(), MessageType.class);

        MessageComplexVo result = messageUserRefMapping.asComplex(entity);
        result.setMessageType(refType.getCodeStr());

        Rules.create().rule(RefType.NoticeMessage, Rules.Runnable(() -> {
                NoticeMessage message = noticeMessageService.getById(refId);
                result.setNoticeMessage(asNoticeMessageComplexVo(message));
            }))
            .rule(RefType.PersonalMessage, Rules.Runnable(() -> {
                PersonalMessage message = personalMessageService.getById(refId);
                result.setPersonalMessage(asPersonalMessageComplexVo(message));
            }))
            .rule(RefType.SystemMessage, Rules.Runnable(() -> {
                SystemMessage message = systemMessageService.getById(refId);
                result.setSystemMessage(asSystemMessageComplexVo(message));
            })).of(refType);
        return result;
    }
}
