package com.github.lotus.chaos.biz.modules.com.service;

import com.github.lotus.chaos.api.modules.com.pojo.ro.UploadFileRo;
import com.github.lotus.chaos.api.modules.com.pojo.vo.FileVo;
import com.github.lotus.chaos.biz.modules.com.entity.File;
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