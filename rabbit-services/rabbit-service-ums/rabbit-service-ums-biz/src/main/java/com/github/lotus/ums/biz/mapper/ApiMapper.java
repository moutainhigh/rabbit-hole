package com.github.lotus.ums.biz.mapper;

import com.github.lotus.ums.biz.entity.Api;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [权限模块] 接口表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface ApiMapper extends BaseMapper<Api> {

    List<Api> listByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<Api> listByUserGroupIds(@Param("userGroupIds") List<Long> userGroupIds);
}
