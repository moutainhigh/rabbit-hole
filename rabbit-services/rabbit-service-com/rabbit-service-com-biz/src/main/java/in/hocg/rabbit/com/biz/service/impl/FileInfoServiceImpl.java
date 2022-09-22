package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import in.hocg.rabbit.com.biz.entity.FileInfo;
import in.hocg.rabbit.com.biz.mapper.FileInfoMapper;
import in.hocg.rabbit.com.biz.service.FileInfoService;
import in.hocg.rabbit.com.biz.utils.Avatars;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.oss.autoconfigure.core.OssFileBervice;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.common.datadict.common.RefType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * [基础模块] 文件引用表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FileInfoServiceImpl extends AbstractServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
    private final OssFileBervice ossFileService;

    @Override
    public String upload(MultipartFile file) {
        return ossFileService.upload(file);
    }

    @Override
    public String upload(java.io.File file) {
        return ossFileService.upload(file);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(UploadFileRo dto) {
        final Long relId = dto.getRefId();
        ValidUtils.notNull(relId, "上传失败，ID 错误");
        final RefType relType = ICode.ofThrow(dto.getRefType(), RefType.class);
        final List<UploadFileRo.FileDto> files = dto.getFiles();
        deleteByRefTypeAndRefId(relType.getCodeStr(), relId);
        final LocalDateTime now = LocalDateTime.now();
        final Long creator = dto.getCreator();
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        final List<FileInfo> list = files.parallelStream()
            .map(item -> new FileInfo()
                .setRefId(relId)
                .setRefType(relType.getCodeStr())
                .setPriority(item.getPriority())
                .setCreator(creator)
                .setFilename(item.getFilename())
                .setCreatedAt(now)
                .setFileUrl(item.getUrl())
            ).collect(Collectors.toList());
        this.saveBatch(list);
    }

    @Override
    public String getAvatarUrl(Long id) {
        final java.io.File file = Avatars.getAvatarAsPath(id).toFile();
        return ossFileService.upload(file, file.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId) {
        return listByRefTypeAndRefIdOrderBySortDescAndCreatedAtDesc(refType, refId)
            .stream()
            .map(item -> new FileVo().setFilename(item.getFilename()).setUrl(item.getFileUrl()))
            .collect(Collectors.toList());
    }

    private List<FileInfo> listByRefTypeAndRefIdOrderBySortDescAndCreatedAtDesc(@NotNull String relType, @NotNull Long relId) {
        return baseMapper.listByRefTypeAndRefIdOrderBySortDescAndCreatedAtDesc(relType, relId);
    }

    private void deleteByRefTypeAndRefId(@NotNull String relType, @NotNull Long relId) {
        lambdaUpdate().eq(FileInfo::getRefType, relType).eq(FileInfo::getRefId, relId).remove();
    }
}
