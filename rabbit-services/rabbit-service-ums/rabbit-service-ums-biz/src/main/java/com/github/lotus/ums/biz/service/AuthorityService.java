package com.github.lotus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.Authority;
import com.github.lotus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import com.github.lotus.ums.biz.pojo.ro.GrantRoleRo;
import com.github.lotus.ums.biz.pojo.ro.GrantUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.SaveAuthorityRo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityComplexVo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import com.github.lotus.ums.biz.pojo.vo.UserRoleComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 权限表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface AuthorityService extends AbstractService<Authority> {

    AuthorityComplexVo getAuthority(Long id);

    void insertOne(SaveAuthorityRo ro);

    void updateOne(Long id, SaveAuthorityRo ro);

    void deleteOne(Long id);

    List<AuthorityTreeNodeVo> listAuthorityTree(String enabled);

    IPage<UserRoleComplexVo> pagingUserByAuthorityId(Long id, GetAuthorityUserPagingRo ro);

    String generateSql();

    boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri);

    void grantRole(Long authorityId, GrantRoleRo ro);

    void grantUserGroup(Long authorityId, GrantUserGroupRo ro);

    List<String> listByProjectIdAndUserId(Long projectId, Long userId);
}
