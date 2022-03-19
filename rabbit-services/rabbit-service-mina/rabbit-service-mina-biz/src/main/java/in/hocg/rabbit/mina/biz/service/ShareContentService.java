package in.hocg.rabbit.mina.biz.service;

import in.hocg.rabbit.mina.biz.entity.ShareContent;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mina.biz.pojo.ro.ShareContentSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ShareContentVo;

/**
 * <p>
 * [功能模块] 分享内容表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-03-16
 */
public interface ShareContentService extends AbstractService<ShareContent> {

    ShareContentVo getByEncoding(String encoding);

    String create(ShareContentSaveRo ro);
}
