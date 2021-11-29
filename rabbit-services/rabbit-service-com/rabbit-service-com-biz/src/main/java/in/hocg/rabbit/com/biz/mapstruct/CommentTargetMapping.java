package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.CommentTarget;
import in.hocg.rabbit.com.biz.pojo.ro.CommentTargetInsertRo;
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
