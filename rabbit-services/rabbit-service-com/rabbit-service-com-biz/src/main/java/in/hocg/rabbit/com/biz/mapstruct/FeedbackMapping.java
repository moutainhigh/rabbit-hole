package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.Feedback;
import in.hocg.rabbit.com.biz.pojo.ro.FeedbackInsertRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface FeedbackMapping {
    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", source = "ro.userId")
    @Mapping(target = "createdAt", ignore = true)
    Feedback asFeedback(FeedbackInsertRo ro);
}
