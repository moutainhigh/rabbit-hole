package in.hocg.rabbit.rcm.biz.service.impl;

import cn.hutool.core.lang.Assert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.convert.DocConvert;
import in.hocg.rabbit.rcm.biz.entity.Doc;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.rabbit.rcm.biz.mapper.DocMapper;
import in.hocg.rabbit.rcm.biz.mapstruct.DocMapping;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.*;
import in.hocg.rabbit.rcm.biz.service.DocContentService;
import in.hocg.rabbit.rcm.biz.service.DocService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.rcm.biz.service.DocVersionService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * [内容模块] 文档对象表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Service
@UseConvert(DocConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocServiceImpl extends AbstractServiceImpl<DocMapper, Doc> implements DocService {
    private final DocContentService docContentService;
    private final DocVersionService docVersionService;
    private final DocMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PublishedDocVo getPublishedById(Long docId) {
        Doc doc = Assert.notNull(getById(docId), "文档不存在");
        return as(doc, PublishedDocVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DraftDocVo getDraftedById(Long id, Long userId) {
        Doc doc = getByOwnerUser(id, userId).orElseThrow(() -> ServiceException.wrap("文档不存在"));
        return as(doc, DraftDocVo.class);
    }

    @Override
    public DraftDocVo getDraftedById(Long id) {
        return as(getById(id), DraftDocVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushDraftedByOwnerUser(Long id, PushDocContentRo ro, Long ownerUserId) {
        Doc doc = getByOwnerUser(id, ownerUserId).orElseThrow(() -> ServiceException.wrap("文档不存在"));
        docContentService.pushDrafted(doc.getId(), ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushPublishedByOwnerUser(Long id, Long ownerUserId) {
        getByOwnerUser(id, ownerUserId).orElseThrow(() -> ServiceException.wrap("文档不存在"));
        docContentService.pushPublished(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IScroll<HistoryDocContentVo> historyByIdAndOwnerUser(Long id, ScrollRo ro, Long ownerUserId) {
        Doc doc = getByOwnerUser(id, ownerUserId).orElseThrow(() -> ServiceException.wrap("文档不存在"));
        return docContentService.scrollByDocId(doc.getId(), ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createVersion(Long contentId, CreateVersionDocRo ro, Long userId) {
        DocContent content = Assert.notNull(docContentService.getById(contentId));
        Long docId = content.getId();
        getByOwnerUser(docId, userId).orElseThrow(() -> ServiceException.wrap("文档不存在"));
        docVersionService.createVersion(contentId, ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rollbackVersion(Long contentId, RollbackDocRo ro, Long ownerUserId) {
        DocContent content = Assert.notNull(docContentService.getById(contentId));
        Long docId = content.getId();
        getByOwnerUser(docId, ownerUserId).orElseThrow(() -> ServiceException.wrap("文档不存在"));
        docContentService.rollbackVersion(contentId, ro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDoc(CreateDocRo ro) {
        Optional<Doc> docOpt = lambdaQuery().eq(Doc::getRefId, ro.getRefId()).eq(Doc::getRefType, ro.getRefType()).oneOpt();
        if (docOpt.isPresent()) {
            return docOpt.map(CommonEntity::getId).orElseThrow();
        }
        Doc entity = mapping.asDoc(ro);
        save(entity);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishContent(PublishDocTextRo ro) {
        docContentService.publishContent(ro);
    }

    private Optional<Doc> getByOwnerUser(Long docId, Long userId) {
        return lambdaQuery().eq(Doc::getOwnerUserId, userId).eq(CommonEntity::getId, docId).oneOpt();
    }
}
