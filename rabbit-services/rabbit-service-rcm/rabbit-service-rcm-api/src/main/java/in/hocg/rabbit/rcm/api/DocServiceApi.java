package in.hocg.rabbit.rcm.api;

import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.api.pojo.vo.PublishedDocVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ApiOperation("获取已发布的文档")
    @PostMapping(value = CONTEXT_ID + "/getPublishedDoc", headers = RcmServiceName.FEIGN_HEADER)
    PublishedDocVo getPublishedDoc(@RequestParam Long id);

    @ApiOperation("获取待编辑的文档")
    @PostMapping(value = CONTEXT_ID + "/getDraftedDoc", headers = RcmServiceName.FEIGN_HEADER)
    PublishedDocVo getDraftedDoc(@RequestParam Long id);
}
