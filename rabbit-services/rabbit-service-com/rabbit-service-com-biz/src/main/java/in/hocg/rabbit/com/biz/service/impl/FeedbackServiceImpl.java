package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.Feedback;
import in.hocg.rabbit.com.biz.entity.Project;
import in.hocg.rabbit.com.biz.mapper.FeedbackMapper;
import in.hocg.rabbit.com.biz.mapstruct.FeedbackMapping;
import in.hocg.rabbit.com.biz.pojo.ro.FeedbackInsertRo;
import in.hocg.rabbit.com.biz.service.FeedbackService;
import in.hocg.rabbit.com.biz.service.ProjectService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [通用模块] 用户反馈表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FeedbackServiceImpl extends AbstractServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    private final FeedbackMapping mapping;
    private final ProjectService projectService;

    @Override
    public void insertOne(FeedbackInsertRo ro) {
        LocalDateTime now = LocalDateTime.now();
        String projectSn = ro.getProjectSn();
        Optional<Project> projectOpt = projectService.getByProjectSn(projectSn);
        Long projectId = projectOpt.map(Project::getId).orElse(null);
        ValidUtils.isNull(projectId, "项目参数错误");

        Feedback entity = mapping.asFeedback(ro);
        entity.setProjectId(projectId);
        entity.setCreatedAt(now);
        this.validInsert(entity);
    }
}
