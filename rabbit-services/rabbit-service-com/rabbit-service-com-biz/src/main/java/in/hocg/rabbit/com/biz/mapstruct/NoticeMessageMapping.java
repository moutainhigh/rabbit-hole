package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.NoticeMessage;
import in.hocg.rabbit.com.biz.pojo.dto.SendNoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.vo.message.NoticeMessageComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface NoticeMessageMapping {


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    NoticeMessage asNoticeMessage(SendNoticeMessageDto dto);

    @Mapping(target = "refTypeName", ignore = true)
    @Mapping(target = "eventTypeName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "refObject", ignore = true)
    NoticeMessageComplexVo asComplex(NoticeMessage entity);
}
