package in.hocg.rabbit.com.api;


import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by hocgin on 2020/6/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ComServiceName.NAME)
public interface FileServiceApi {
    String CONTEXT_ID = "FileServiceApi";

    @PostMapping(value = CONTEXT_ID + "/upload", headers = ComServiceName.FEIGN_HEADER)
    void upload(@Validated @RequestBody UploadFileRo ro);

    @PostMapping(value = CONTEXT_ID + "/upload2", headers = ComServiceName.FEIGN_HEADER)
    String upload(@RequestParam("file") java.io.File file);

    @PostMapping(value = CONTEXT_ID + "/listFileByRefTypeAndRefId", headers = ComServiceName.FEIGN_HEADER)
    List<FileVo> listByRefTypeAndRefId(@RequestParam("refType") String refType, @RequestParam("refId") Long refId);

    @PostMapping(value = CONTEXT_ID + "/getAvatarUrl", headers = ComServiceName.FEIGN_HEADER)
    String getAvatarUrl(@RequestParam("id") Long id);
}
