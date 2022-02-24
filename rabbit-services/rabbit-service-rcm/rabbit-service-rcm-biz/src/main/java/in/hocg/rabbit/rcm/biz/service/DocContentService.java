package in.hocg.rabbit.rcm.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.HistoryDocContentVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.RollbackDocRo;

import java.util.Optional;

/**
 * <p>
 * [内容模块] 文档内容表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
public interface DocContentService extends AbstractService<DocContent> {

    Optional<DocContent> getPublishedByDocId(Long docId);

    Optional<DocContent> getDraftedByDocId(Long docId);

    Long pushDrafted(Long docId, PushDocContentRo ro);

    void pushPublished(Long docId);

    IScroll<HistoryDocContentVo> scrollByDocId(Long docId, ScrollRo ro);

    void rollbackVersion(Long contentId, RollbackDocRo ro);

    void publishContent(PublishDocTextRo ro);
}
