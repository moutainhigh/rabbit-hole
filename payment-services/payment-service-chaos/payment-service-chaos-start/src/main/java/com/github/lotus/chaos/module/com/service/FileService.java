package com.github.lotus.chaos.module.com.service;

import com.github.lotus.chaos.module.com.entity.File;
import com.github.lotus.chaos.modules.com.ro.UploadFileRo;
import com.github.lotus.chaos.modules.com.vo.FileVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * [基础模块] 文件引用表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
public interface FileService extends AbstractService<File> {

    String upload(MultipartFile file);

    void upload(UploadFileRo dto);

    List<FileVo> listFileByRelTypeAndRelId(@NotNull String relType,
                                           @NotNull Long relId);
}
