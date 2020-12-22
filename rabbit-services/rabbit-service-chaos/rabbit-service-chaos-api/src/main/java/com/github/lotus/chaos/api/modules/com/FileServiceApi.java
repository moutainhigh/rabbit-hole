package com.github.lotus.chaos.api.modules.com;


import com.github.lotus.chaos.api.ServiceName;
import com.github.lotus.chaos.api.modules.com.pojo.ro.UploadFileRo;
import com.github.lotus.chaos.api.modules.com.pojo.vo.FileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2020/6/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = FileServiceApi.CONTEXT_ID)
public interface FileServiceApi {
    String CONTEXT_ID = "FileServiceApi";

    @PostMapping(CONTEXT_ID + "/upload")
    void upload(@Validated @RequestBody UploadFileRo ro);

    @PostMapping(CONTEXT_ID + "/listFileByRelTypeAndRelId")
    List<FileVo> listFileByRelTypeAndRelId(@RequestParam("relType") String relType,
                                           @RequestParam("relId") Long relId);

    @PostMapping(CONTEXT_ID + "/getAvatarUrl")
    String getAvatarUrl(@RequestParam("id") Long id);
}
