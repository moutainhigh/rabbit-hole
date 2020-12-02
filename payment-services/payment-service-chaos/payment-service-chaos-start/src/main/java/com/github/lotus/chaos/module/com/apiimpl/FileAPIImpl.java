package com.github.lotus.chaos.module.com.apiimpl;

import com.github.lotus.chaos.module.com.service.FileService;
import com.github.lotus.chaos.modules.com.api.FileAPI;
import com.github.lotus.chaos.modules.com.api.ro.UploadFileRo;
import com.github.lotus.chaos.modules.com.api.vo.FileVo;
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
public class FileAPIImpl implements FileAPI {
    private final FileService service;

    @Override
    public void upload(UploadFileRo ro) {
        service.upload(ro);
    }

    @Override
    public List<FileVo> listFileByRelTypeAndRelId(String relType, Long relId) {
        return service.listFileByRelTypeAndRelId(relType, relId);
    }
}
