package com.github.lotus.chaos.biz.modules.com.apiimpl;

import com.github.lotus.chaos.api.modules.com.FileServiceApi;
import com.github.lotus.chaos.api.modules.com.pojo.ro.UploadFileRo;
import com.github.lotus.chaos.api.modules.com.pojo.vo.FileVo;
import com.github.lotus.chaos.biz.modules.com.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2020/6/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FileServiceApiImpl implements FileServiceApi {
    private final FileService service;

    @Override
    public void upload(UploadFileRo ro) {
        service.upload(ro);
    }

    @Override
    public List<FileVo> listFileByRelTypeAndRelId(String relType, Long relId) {
        return service.listFileByRelTypeAndRelId(relType, relId);
    }

    @Override
    public String getAvatarUrl(Long id) {
        return service.getAvatarUrl(id);
    }
}
