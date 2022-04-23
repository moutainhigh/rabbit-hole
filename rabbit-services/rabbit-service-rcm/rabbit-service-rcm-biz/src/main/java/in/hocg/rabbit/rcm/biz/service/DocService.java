package in.hocg.rabbit.rcm.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.entity.Doc;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.rcm.biz.pojo.ro.CreateVersionDocRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.RollbackDocRo;
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

    /**
     * 获取发布内容
     *
     * @param docId
     * @return
     */
    PublishedDocVo getPublishedById(Long docId);

    /**
     * 获取草稿内容
     *
     * @param id
     * @param userId
     * @return
     */
    DraftDocVo getDraftedById(Long id, Long userId);

    /**
     * 获取草稿内容
     *
     * @param id
     * @return
     */
    DraftDocVo getDraftedById(Long id);

    /**
     * 保存草稿内容
     *
     * @param id
     * @param ro
     * @param ownerUserId
     */
    void pushDraftedByOwnerUser(Long id, PushDocContentRo ro, Long ownerUserId);

    /**
     * 保存发布内容
     *
     * @param id
     * @param ownerUserId
     */
    void pushPublishedByOwnerUser(Long id, Long ownerUserId);

    /**
     * 查询历史内容
     *
     * @param id
     * @param ro
     * @param ownerUserId
     * @return
     */
    IScroll<HistoryDocContentVo> historyByIdAndOwnerUser(Long id, ScrollRo ro, Long ownerUserId);

    /**
     * 创建版本
     *
     * @param contentId
     * @param ro
     * @param userId
     */
    void createVersion(Long contentId, CreateVersionDocRo ro, Long userId);

    /**
     * 回滚版本
     *
     * @param contentId
     * @param ro
     * @param ownerUserId
     */
    void rollbackVersion(Long contentId, RollbackDocRo ro, Long ownerUserId);

    /**
     * 创建文档
     *
     * @param ro
     * @return
     */
    Long createDoc(CreateDocRo ro);

    /**
     * 发布内容
     *
     * @param ro
     */
    void publishContent(PublishDocTextRo ro);
}
