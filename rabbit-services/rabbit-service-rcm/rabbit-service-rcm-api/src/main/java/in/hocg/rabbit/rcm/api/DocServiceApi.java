package in.hocg.rabbit.rcm.api;

import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by hocgin on 2022/2/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = RcmServiceName.NAME)
public interface DocServiceApi {
    String CONTEXT_ID = "DocServiceApi";

    @ApiOperation("创建文档")
    @PostMapping(value = CONTEXT_ID + "/createDoc", headers = RcmServiceName.FEIGN_HEADER)
    Long createDoc(@Validated @RequestBody CreateDocRo ro);

    @ApiOperation("发布文档内容")
    @PostMapping(value = CONTEXT_ID + "/publishContent", headers = RcmServiceName.FEIGN_HEADER)
    void publishContent(@Validated @RequestBody PublishDocTextRo ro);


}
