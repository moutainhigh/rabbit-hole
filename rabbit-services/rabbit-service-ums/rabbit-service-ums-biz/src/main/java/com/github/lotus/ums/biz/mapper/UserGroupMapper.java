package com.github.lotus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.entity.UserGroup;
import com.github.lotus.ums.biz.pojo.ro.UserGroupPagingRo;
import com.github.lotus.ums.biz.pojo.vo.RoleComplexVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [权限模块] 用户组表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {

    IPage<UserGroup> paging(UserGroupPagingRo ro);
}
