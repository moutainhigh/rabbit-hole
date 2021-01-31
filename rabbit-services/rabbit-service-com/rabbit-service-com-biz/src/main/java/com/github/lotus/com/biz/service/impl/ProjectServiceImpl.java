package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.com.biz.entity.Project;
import com.github.lotus.com.biz.mapper.ProjectMapper;
import com.github.lotus.com.biz.mapstruct.ProjectMapping;
import com.github.lotus.com.biz.pojo.ro.ProjectCompleteRo;
import com.github.lotus.com.biz.service.ProjectService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final ProjectMapping mapping;

    @Override
    public Optional<Project> getByProjectSn(String projectSn) {
        return lambdaQuery().eq(Project::getEncoding, projectSn).oneOpt();
    }

    @Override
    public ProjectComplexVo getByEncoding(String projectSn) {
        return getByProjectSn(projectSn).map(this::convert).orElse(null);
    }

    @Override
    public List<ProjectComplexVo> complete(ProjectCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage())
            .convert(this::convert).getRecords();
    }

    @Override
    public List<ProjectComplexVo> listComplexById(List<Long> id) {
        return LangUtils.toList(listByIds(id), this::convert);
    }

    private ProjectComplexVo convert(Project entity) {
        return mapping.asComplex(entity);
    }
}
