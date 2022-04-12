package in.hocg.rabbit.owp.biz.service;

import in.hocg.rabbit.owp.api.pojo.vo.DevAppVo;
import in.hocg.rabbit.owp.biz.entity.DeveloperApp;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

/**
 * <p>
 * [开放平台] 开发者应用表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
public interface DeveloperAppService extends AbstractService<DeveloperApp> {

    DevAppVo getByEncoding(String encoding);
}
