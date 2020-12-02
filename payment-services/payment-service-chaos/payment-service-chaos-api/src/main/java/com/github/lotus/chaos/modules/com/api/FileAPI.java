package com.github.lotus.chaos.modules.com.api;



import com.github.lotus.chaos.modules.com.api.ro.UploadFileRo;
import com.github.lotus.chaos.modules.com.api.vo.FileVo;

import java.util.List;

/**
 * Created by hocgin on 2020/6/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface FileAPI {
    void upload(UploadFileRo ro);

    List<FileVo> listFileByRelTypeAndRelId(String relType, Long relId);
}
