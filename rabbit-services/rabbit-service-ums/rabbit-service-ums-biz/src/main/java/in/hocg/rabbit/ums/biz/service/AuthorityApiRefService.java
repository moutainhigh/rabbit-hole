package in.hocg.rabbit.ums.biz.service;

import in.hocg.rabbit.ums.biz.entity.AuthorityApiRef;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 权限x接口表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface AuthorityApiRefService extends AbstractService<AuthorityApiRef> {

    void grantApis(Long id, List<Long> apis);
}
