package in.hocg.rabbit.com.api;

import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.pojo.vo.DocTextVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"文档", ComServiceName.NAME})
@FeignClient(value = ComServiceName.NAME)
public interface DocTextServiceApi {
    String CONTEXT_ID = "DocTextServiceApi";

    @ApiOperation("发布文档")
    @PostMapping(value = CONTEXT_ID + "/publish", headers = ComServiceName.FEIGN_HEADER)
    void publish(@Validated @RequestBody PublishDocTextRo ro);

    @ApiOperation("查询文档")
    @PostMapping(value = CONTEXT_ID + "/listByRefTypeAndRefId", headers = ComServiceName.FEIGN_HEADER)
    List<DocTextVo> listByRefTypeAndRefId(@RequestParam("refType") String refType, @RequestParam("refId") Long refId);
}
