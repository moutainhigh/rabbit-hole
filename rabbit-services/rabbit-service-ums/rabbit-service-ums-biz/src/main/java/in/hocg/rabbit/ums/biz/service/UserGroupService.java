package in.hocg.rabbit.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.ums.biz.entity.UserGroup;
import in.hocg.rabbit.ums.biz.pojo.ro.*;
import in.hocg.rabbit.ums.biz.pojo.vo.UserGroupComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserGroupRefUserVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

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

    UserGroupComplexVo getComplex(Long userGroupId);

    void insertOne(SaveUserGroupRo ro);

    IPage<UserGroupComplexVo> paging(UserGroupPagingRo ro);

    void updateOne(Long userGroupId, SaveUserGroupRo ro);

    void deleteOne(Long userGroupId);

    void assignUserGroup(Long userGroupId, AssignUserGroupRo ro);

    List<UserGroupComplexVo> getComplete(UserGroupCompleteRo ro);

    void grantAuthority(Long userGroupId, UserGroupGrantAuthorityRo ro);

    IPage<UserGroupRefUserVo> pagingUser(UserGroupRefUserPagingRo ro);
}
