package com.github.lotus.com.biz.apiimpl;

import com.github.lotus.com.api.ProjectServiceApi;
import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.com.biz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProjectServiceApiImpl implements ProjectServiceApi {
    private final ProjectService service;

    @Override
    public ProjectComplexVo getComplexByProjectSn(String projectSn) {
        return service.getByEncoding(projectSn);
    }

    @Override
    public List<ProjectComplexVo> listComplexById(List<Long> id) {
        return service.listComplexById(id);
    }
}
