package com.github.lotus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.Role;
import com.github.lotus.ums.biz.pojo.ro.RolePagingRo;
import com.github.lotus.ums.biz.pojo.ro.SaveRoleRo;
import com.github.lotus.ums.biz.pojo.vo.RoleComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface RoleService extends AbstractService<Role> {

    RoleComplexVo getRole(Long roleId);

    void insertOne(SaveRoleRo ro);

    IPage<RoleComplexVo> paging(RolePagingRo ro);

    void updateOne(Long id, SaveRoleRo ro);

    void deleteOne(Long id);

    List<Role> listByUserId(Long userId);
}
