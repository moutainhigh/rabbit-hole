package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.PersonalMessage;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.vo.message.PersonalMessageComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PersonalMessageMapping {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PersonalMessage asPersonalMessage(SendPersonalMessageDto dto);

    @Mapping(target = "creatorName", ignore = true)
    PersonalMessageComplexVo asComplex(PersonalMessage message);
}
