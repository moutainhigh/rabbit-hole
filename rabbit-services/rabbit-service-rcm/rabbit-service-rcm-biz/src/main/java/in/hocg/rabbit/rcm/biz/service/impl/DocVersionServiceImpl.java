package in.hocg.rabbit.rcm.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.rabbit.rcm.biz.entity.DocVersion;
import in.hocg.rabbit.rcm.biz.mapper.DocVersionMapper;
import in.hocg.rabbit.rcm.biz.pojo.vo.CreateVersionDocRo;
import in.hocg.rabbit.rcm.biz.service.DocVersionService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * <p>
 * [内容模块] 文档内容版本表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocVersionServiceImpl extends AbstractServiceImpl<DocVersionMapper, DocVersion> implements DocVersionService {

    @Override
    public Optional<DocVersion> getByDocContentId(Long id) {
        return lambdaQuery().eq(DocVersion::getDocContentId, id).oneOpt();
    }

    @Override
    public void createVersion(Long contentId, CreateVersionDocRo ro) {
        boolean hasDocContent = has(DocVersion::getDocContentId, contentId, CommonEntity::getId);
        if (hasDocContent) {
            return;
        }
        DocVersion version = new DocVersion();
        version.setDocContentId(contentId);
        version.setTitle(ro.getTitle());
        save(version);
    }
}
