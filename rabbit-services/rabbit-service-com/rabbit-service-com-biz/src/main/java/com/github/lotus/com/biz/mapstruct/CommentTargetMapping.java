package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.CommentTarget;
import com.github.lotus.com.biz.pojo.ro.CommentTargetInsertRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface CommentTargetMapping {
    @Mapping(target = "refType", ignore = true)
    @Mapping(target = "refId", ignore = true)
    @Mapping(target = "id", ignore = true)
    CommentTarget asCommentTarget(CommentTargetInsertRo ro);
}
