package in.hocg.rabbit.com.api;

import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.common.constant.GlobalConstant;
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
@FeignClient(value = ServiceName.NAME)
public interface ProjectServiceApi {
    String CONTEXT_ID = "ProjectServiceApi";

    @PostMapping(value = CONTEXT_ID + "/getProjectByProjectSn", headers = GlobalConstant.FEIGN_HEADER)
    ProjectComplexVo getComplexByProjectSn(@RequestParam("projectSn") String projectSn);

    @PostMapping(value = CONTEXT_ID + "/listComplexById", headers = GlobalConstant.FEIGN_HEADER)
    List<ProjectComplexVo> listComplexById(@RequestParam("id") List<Long> id);
}
