package in.hocg.rabbit.rcm.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.rabbit.common.constant.GlobalConstant;
import in.hocg.rabbit.common.utils.DbUtils;
import in.hocg.rabbit.common.utils.TextUtils;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.convert.DocContentConvert;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.rabbit.rcm.biz.mapper.DocContentMapper;
import in.hocg.rabbit.rcm.biz.mapstruct.DocContentMapping;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.HistoryDocContentVo;
import in.hocg.rabbit.rcm.biz.pojo.ro.RollbackDocRo;
import in.hocg.rabbit.rcm.biz.service.DocContentService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * [内容模块] 文档内容表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Service
@UseConvert(DocContentConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocContentServiceImpl extends AbstractServiceImpl<DocContentMapper, DocContent> implements DocContentService {
    private final DocContentMapping mapping;

    @Override
    public Optional<DocContent> getPublishedByDocId(Long docId) {
        return lambdaQuery().eq(DocContent::getDocId, docId)
            .isNull(DocContent::getDropFlag).oneOpt();
    }

    @Override
    public Optional<DocContent> getDraftedByDocId(Long docId) {
        return first(lambdaQuery().eq(DocContent::getDocId, docId)
            .orderByDesc(CommonEntity::getCreatedAt));
    }

    @Override
    public Long pushDrafted(Long docId, PushDocContentRo ro) {
        String content = ro.getContent();

        String summary = TextUtils.getSummary(content, 200);
        String keyword = DbUtils.toString(TextUtils.getKeyword(content, 5));
        String title = ro.getTitle();
        DocContent entity = mapping.asDocContent(ro).setDocId(docId)
            .setKeyword(keyword)
            .setTitle(title)
            .setSummary(summary);
        save(entity);
        return entity.getId();
    }

    @Override
    public void pushPublished(Long docId) {
        getDraftedByDocId(docId).ifPresent(docContent -> updatePublishedById(docContent.getId()));
    }

    @Override
    public IScroll<HistoryDocContentVo> scrollByDocId(Long docId, ScrollRo ro) {
        Serializable nextId = ro.getNextId();
        Page<DocContent> result = lambdaQuery().eq(DocContent::getDocId, docId).gt(Objects.nonNull(nextId), CommonEntity::getId, nextId)
            .orderByDesc(CommonEntity::getCreatedAt)
            .page(ro.ofPage());
        return as(PageUtils.fillScroll(result, CommonEntity::getId), HistoryDocContentVo.class);
    }

    @Override
    public void rollbackVersion(Long contentId, RollbackDocRo ro) {
        DocContent content = Assert.notNull(getById(contentId), "文档内容不存在");
        DocContent entity = new DocContent();
        entity.setContent(content.getContent());
        entity.setSummary(content.getSummary());
        entity.setTitle(content.getTitle());
        entity.setKeyword(content.getKeyword());
        entity.setDoctype(content.getDoctype());
        entity.setDocId(content.getDocId());
        save(entity);
        Long newContentId = entity.getId();
        updatePublishedById(newContentId);
    }

    @Override
    public void publishContent(PublishDocTextRo ro) {
        PushDocContentRo pushDocContentRo = new PushDocContentRo();
        pushDocContentRo.setTitle(ro.getTitle());
        pushDocContentRo.setContent(ro.getContent());
        pushDocContentRo.setDoctype(ro.getDoctype());
        Long contentId = pushDrafted(ro.getDocId(), pushDocContentRo);
        if (ObjectUtil.defaultIfNull(ro.getPublished(), false)) {
            updatePublishedById(contentId);
        }
    }

    private void updatePublishedById(Long id) {
        DocContent docContent = getById(id);
        Long docId = docContent.getDocId();

        // 原发布内容更新为作废
        lambdaUpdate().eq(DocContent::getDocId, docId)
            .ne(CommonEntity::getId, id)
            .isNull(DocContent::getDropFlag)
            .set(DocContent::getDropFlag, LocalDateTime.now())
            .update();

        // 草稿标记为发布
        lambdaUpdate().eq(DocContent::getId, docContent.getId())
            .isNotNull(DocContent::getDropFlag)
            .set(DocContent::getDropFlag, null)
            .update();
    }

}
