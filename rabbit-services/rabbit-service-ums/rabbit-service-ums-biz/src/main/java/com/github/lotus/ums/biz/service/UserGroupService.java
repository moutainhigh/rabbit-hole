package com.github.lotus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.pojo.ro.AssignUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.SaveUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.UserGroupPagingRo;
import com.github.lotus.ums.biz.pojo.vo.RoleComplexVo;
import com.github.lotus.ums.biz.pojo.vo.UserGroupComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface UserGroupService extends AbstractService<UserGroup> {

    List<UserGroup> listByUserId(Long userId);

    UserGroupComplexVo getUserGroup(Long userGroupId);

    void insertOne(SaveUserGroupRo ro);

    IPage<UserGroupComplexVo> paging(UserGroupPagingRo ro);

    void updateOne(Long userGroupId, SaveUserGroupRo ro);

    void deleteOne(Long userGroupId);

    void assignUserGroup(Long userGroupId, AssignUserGroupRo ro);
}
