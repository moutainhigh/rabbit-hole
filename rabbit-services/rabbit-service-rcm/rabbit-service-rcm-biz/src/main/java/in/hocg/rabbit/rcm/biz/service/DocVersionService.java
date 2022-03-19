package in.hocg.rabbit.rcm.biz.service;

import in.hocg.rabbit.rcm.biz.entity.DocVersion;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.rcm.biz.pojo.vo.CreateVersionDocRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.RollbackDocRo;

import java.util.Optional;

/**
 * <p>
 * [内容模块] 文档内容版本表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
public interface DocVersionService extends AbstractService<DocVersion> {

    Optional<DocVersion> getByDocContentId(Long id);

    void createVersion(Long contentId, CreateVersionDocRo ro);
}
