package com.github.lotus.com.biz.service;

import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.com.biz.entity.Project;
import com.github.lotus.com.biz.pojo.ro.ProjectCompleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [通用模块] 项目表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
public interface ProjectService extends AbstractService<Project> {

    Optional<Project> getByProjectSn(String projectSn);

    ProjectComplexVo getByEncoding(String projectSn);

    List<ProjectComplexVo> complete(ProjectCompleteRo ro);

    List<ProjectComplexVo> listComplexById(List<Long> id);
}
