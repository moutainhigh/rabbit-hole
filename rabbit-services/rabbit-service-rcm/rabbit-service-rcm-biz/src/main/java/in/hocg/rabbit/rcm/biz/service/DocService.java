package in.hocg.rabbit.rcm.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.entity.Doc;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.*;

/**
 * <p>
 * [内容模块] 文档对象表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
public interface DocService extends AbstractService<Doc> {

    PublishedDocVo getPublishedById(Long docId);

    DraftDocVo getDraftedById(Long id, Long userId);

    void pushDraftedByOwnerUser(Long id, PushDocContentRo ro, Long ownerUserId);

    void pushPublishedByOwnerUser(Long id, Long ownerUserId);

    IScroll<HistoryDocContentVo> historyByIdAndOwnerUser(Long id, ScrollRo ro, Long ownerUserId);

    void createVersion(Long contentId, CreateVersionDocRo ro, Long userId);

    void rollbackVersion(Long contentId, RollbackDocRo ro, Long ownerUserId);

    Long createDoc(CreateDocRo ro);

    void publishContent(PublishDocTextRo ro);
}
