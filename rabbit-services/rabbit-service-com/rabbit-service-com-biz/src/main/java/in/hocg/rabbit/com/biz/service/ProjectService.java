package in.hocg.rabbit.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.com.biz.entity.Project;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectCompleteRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectPagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectSaveRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

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

    void deleteOne(Long id);

    IPage<ProjectComplexVo> paging(ProjectPagingRo ro);

    void updateOne(Long id, ProjectSaveRo ro);

    void insertOne(ProjectSaveRo ro);

    ProjectComplexVo getComplex(Long id);
}
