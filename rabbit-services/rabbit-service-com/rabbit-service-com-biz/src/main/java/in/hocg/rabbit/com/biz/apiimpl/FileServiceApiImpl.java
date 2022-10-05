package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.FileServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import in.hocg.rabbit.com.biz.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2020/6/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FileServiceApiImpl implements FileServiceApi {
    private final FileInfoService service;

    @Override
    public void upload(UploadFileRo ro) {
        service.upload(ro);
    }

    @Override
    public String upload(java.io.File file) {
        return service.upload(file);
    }

    @Override
    public List<FileVo> listByRefTypeAndRefId(String relType, Long relId) {
        return service.listByRefTypeAndRefId(relType, relId);
    }

    @Override
    public String getAvatarUrl(Long id) {
        return service.getAvatarUrl(id);
    }
}
