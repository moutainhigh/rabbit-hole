package com.github.lotus.chaos.biz.modules.com.service.impl;

import com.github.lotus.chaos.api.modules.com.api.ro.UploadFileRo;
import com.github.lotus.chaos.api.modules.com.api.vo.FileVo;
import com.github.lotus.chaos.biz.modules.com.entity.File;
import com.github.lotus.chaos.biz.modules.com.enums.FileRelType;
import com.github.lotus.chaos.biz.modules.com.mapper.FileMapper;
import com.github.lotus.chaos.biz.modules.com.service.FileService;
import com.github.lotus.common.constant.GlobalConstant;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.constant.CodeEnum;
import in.hocg.boot.oss.autoconfigure.core.OssFileService;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
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
public class FileServiceImpl extends AbstractServiceImpl<FileMapper, File> implements FileService {
    private final OssFileService ossFileService;

    @Override
    public String upload(MultipartFile file) {
        return ossFileService.upload(file);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(UploadFileRo dto) {
        final Long relId = dto.getRelId();
        ValidUtils.notNull(relId, "上传失败，ID 错误");
        final FileRelType relType = CodeEnum.ofThrow(dto.getRelType(), FileRelType.class);
        final List<UploadFileRo.FileDto> files = dto.getFiles();
        deleteByRelTypeAndRelId(relType, relId);
        final LocalDateTime now = LocalDateTime.now();
        final Long creator = LangUtils.getOrDefault(dto.getCreator(), GlobalConstant.SUPPER_ADMIN_USER_ID);
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        final List<File> list = files.parallelStream()
            .map(item -> new File()
                .setRelId(relId)
                .setRelType((String) relType.getCode())
                .setSort(item.getSort())
                .setCreator(creator)
                .setFilename(item.getFilename())
                .setCreatedAt(now)
                .setFileUrl(item.getUrl())
            ).collect(Collectors.toList());
        this.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileVo> listFileByRelTypeAndRelId(@NotNull String relType,
                                                  @NotNull Long relId) {
        return listFileByRelTypeAndRelIdOrderBySortDescAndCreatedAtDesc(CodeEnum.ofThrow(relType, FileRelType.class), relId)
            .stream()
            .map(item -> new FileVo().setFilename(item.getFilename()).setUrl(item.getFileUrl()))
            .collect(Collectors.toList());
    }

    private List<File> listFileByRelTypeAndRelIdOrderBySortDescAndCreatedAtDesc(@NotNull FileRelType relType, @NotNull Long relId) {
        return baseMapper.listFileByRelTypeAndRelIdOrderBySortDescAndCreatedAtDesc(relType.getCode(), relId);
    }

    private void deleteByRelTypeAndRelId(@NotNull FileRelType relType, @NotNull Long relId) {
        lambdaUpdate().eq(File::getRelType, relType.getCode())
            .eq(File::getRelId, relId)
            .remove();
    }
}
