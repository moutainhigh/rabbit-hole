package in.hocg.rabbit.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.com.biz.entity.Project;
import in.hocg.rabbit.com.biz.mapper.ProjectMapper;
import in.hocg.rabbit.com.biz.mapstruct.ProjectMapping;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectCompleteRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectPagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectSaveRo;
import in.hocg.rabbit.com.biz.service.ProjectService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        return getByProjectSn(projectSn).map(this::convertComplex).orElse(null);
    }

    @Override
    public List<ProjectComplexVo> complete(ProjectCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage())
            .convert(this::convertComplex).getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProjectComplexVo> listComplexById(List<Long> id) {
        return LangUtils.toList(listByIds(id), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<ProjectComplexVo> paging(ProjectPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, ProjectSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        Project entity = mapping.asProject(ro);
        entity.setId(id);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(userId);
        validUpdateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(ProjectSaveRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        Project entity = mapping.asProject(ro);
        entity.setCreatedAt(now);
        entity.setCreator(userId);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectComplexVo getComplex(Long id) {
        return this.convertComplex(getById(id));
    }

    private ProjectComplexVo convertComplex(Project entity) {
        return mapping.asComplex(entity);
    }
}
