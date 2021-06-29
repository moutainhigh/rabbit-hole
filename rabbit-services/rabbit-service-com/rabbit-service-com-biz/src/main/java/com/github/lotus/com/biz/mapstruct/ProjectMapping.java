package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.com.biz.entity.Project;
import com.github.lotus.com.biz.pojo.ro.ProjectSaveRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ProjectMapping {
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    ProjectComplexVo asComplex(Project entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "config", ignore = true)
    Project asProject(ProjectSaveRo ro);
}
