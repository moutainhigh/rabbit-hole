package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.Project;
import com.github.lotus.com.biz.mapper.ProjectMapper;
import com.github.lotus.com.biz.service.ProjectService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * <p>
 * [通用模块] 项目表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProjectServiceImpl extends AbstractServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public Optional<Project> getByProjectSn(String projectSn) {
        return lambdaQuery().eq(Project::getProjectSn, projectSn).oneOpt();
    }

}
