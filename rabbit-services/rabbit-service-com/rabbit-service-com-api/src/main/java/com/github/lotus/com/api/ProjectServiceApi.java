package com.github.lotus.com.api;

import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = ProjectServiceApi.CONTEXT_ID)
public interface ProjectServiceApi {
    String CONTEXT_ID = "ProjectServiceApi";

    @PostMapping(CONTEXT_ID + "/getProjectByProjectSn")
    ProjectComplexVo getComplexByProjectSn(@RequestParam("projectSn") String projectSn);

    @PostMapping(CONTEXT_ID + "/listComplexById")
    List<ProjectComplexVo> listComplexById(@RequestParam("id") List<Long> id);
}
