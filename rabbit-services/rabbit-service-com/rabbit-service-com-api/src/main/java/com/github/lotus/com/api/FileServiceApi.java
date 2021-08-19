package com.github.lotus.com.api;


import com.github.lotus.com.api.pojo.ro.UploadFileRo;
import com.github.lotus.com.api.pojo.vo.FileVo;
import com.github.lotus.common.constant.GlobalConstant;
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
@FeignClient(value = ServiceName.NAME)
public interface FileServiceApi {
    String CONTEXT_ID = "FileServiceApi";

    @PostMapping(value = CONTEXT_ID + "/upload", headers = GlobalConstant.FEIGN_HEADER)
    void upload(@Validated @RequestBody UploadFileRo ro);

    @PostMapping(value = CONTEXT_ID + "/listFileByRefTypeAndRefId", headers = GlobalConstant.FEIGN_HEADER)
    List<FileVo> listByRefTypeAndRefId(@RequestParam("refType") String refType, @RequestParam("refId") Long refId);

    @PostMapping(value = CONTEXT_ID + "/getAvatarUrl", headers = GlobalConstant.FEIGN_HEADER)
    String getAvatarUrl(@RequestParam("id") Long id);
}
