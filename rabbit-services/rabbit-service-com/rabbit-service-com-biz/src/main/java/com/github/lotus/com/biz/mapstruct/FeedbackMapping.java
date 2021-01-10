package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.Feedback;
import com.github.lotus.com.biz.pojo.ro.FeedbackPostRo;
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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", source = "ro.userId")
    @Mapping(target = "createdAt", ignore = true)
    Feedback asFeedback(FeedbackPostRo ro);
}
