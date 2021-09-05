package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.CommentUserAction;
import com.github.lotus.com.biz.pojo.dto.CreateCommentUserActionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/9/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface CommentUserActionMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommentUserAction asCommentUserAction(CreateCommentUserActionDto dto);
}
