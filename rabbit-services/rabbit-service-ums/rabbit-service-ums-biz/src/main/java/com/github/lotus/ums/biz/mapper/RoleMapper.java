package com.github.lotus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.ums.biz.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.ums.biz.pojo.ro.RolePagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [权限模块] 角色表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    IPage<Role> paging(@Param("ro") RolePagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
