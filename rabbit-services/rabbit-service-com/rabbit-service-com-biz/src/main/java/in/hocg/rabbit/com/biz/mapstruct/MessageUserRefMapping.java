package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendSystemMessageDto;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendPersonalMessageRo;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendSystemMessageRo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface MessageUserRefMapping {

    @Mapping(target = "receiverUserName", ignore = true)
    @Mapping(target = "messageTypeName", ignore = true)
    @Mapping(target = "systemMessage", ignore = true)
    @Mapping(target = "personalMessage", ignore = true)
    @Mapping(target = "noticeMessage", ignore = true)
    @Mapping(target = "messageType", ignore = true)
    MessageComplexVo asComplex(MessageUserRef entity);

    @Mapping(target = "creator", ignore = true)
    SendPersonalMessageDto asSendPersonalMessageDto(SendPersonalMessageRo ro);

    @Mapping(target = "creator", ignore = true)
    SendSystemMessageDto asSendSystemMessageDto(SendSystemMessageRo ro);
}
