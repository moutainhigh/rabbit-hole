package in.hocg.rabbit.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.entity.UserGroupAuthorityRef;
import in.hocg.rabbit.ums.biz.pojo.ro.UserGroupRefUserPagingRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组x权限表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface UserGroupAuthorityRefService extends AbstractService<UserGroupAuthorityRef> {

    void grantAuthority(Long userGroupId, Long authorityId);

    boolean hasUserGroupByAuthorityId(Long authorityId);

    void grantAuthorities(Long userGroupId, List<Long> authorities);

    IPage<User> pagingUser(UserGroupRefUserPagingRo ro);
}
