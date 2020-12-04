package com.github.lotus.chaos.biz.modules.com.apiimpl;

import com.github.lotus.chaos.api.modules.com.api.FileAPI;
import com.github.lotus.chaos.api.modules.com.api.ro.UploadFileRo;
import com.github.lotus.chaos.api.modules.com.api.vo.FileVo;
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
