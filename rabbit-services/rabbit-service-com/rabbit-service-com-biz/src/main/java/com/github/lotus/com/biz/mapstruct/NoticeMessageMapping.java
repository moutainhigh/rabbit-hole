package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.NoticeMessage;
import com.github.lotus.com.biz.pojo.dto.SendNoticeMessageDto;
import com.github.lotus.com.biz.pojo.vo.message.NoticeMessageComplexVo;
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

    @Mapping(target = "refObject", ignore = true)
    NoticeMessageComplexVo asComplex(NoticeMessage entity);
}
