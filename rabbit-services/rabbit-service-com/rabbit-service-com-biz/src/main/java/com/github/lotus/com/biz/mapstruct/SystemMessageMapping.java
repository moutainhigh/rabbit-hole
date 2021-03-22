package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.SystemMessage;
import com.github.lotus.com.biz.pojo.dto.SendSystemMessageDto;
import com.github.lotus.com.biz.pojo.vo.message.SystemMessageComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface SystemMessageMapping {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    SystemMessage asSystemMessage(SendSystemMessageDto dto);

    SystemMessageComplexVo asComplex(SystemMessage message);
}
