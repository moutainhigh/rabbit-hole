package com.github.lotus.com.biz.apiimpl;

import com.github.lotus.com.api.FileServiceApi;
import com.github.lotus.com.api.pojo.ro.UploadFileRo;
import com.github.lotus.com.api.pojo.vo.FileVo;
import com.github.lotus.com.biz.service.FileService;
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
    private final FileService service;

    @Override
    public void upload(UploadFileRo ro) {
        service.upload(ro);
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
