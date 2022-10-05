package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import in.hocg.rabbit.com.biz.entity.FileInfo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
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
public interface FileInfoService extends AbstractService<FileInfo> {

    String upload(MultipartFile file);

    String upload(java.io.File file);

    void upload(UploadFileRo dto);

    String getAvatarUrl(Long id);

    List<FileVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId);
}
