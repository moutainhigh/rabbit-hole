package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.com.biz.entity.Project;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ProjectMapping {
    ProjectComplexVo asComplex(Project entity);
}
