package in.hocg.rabbit.ums.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.ums.biz.entity.UserGroup;
import in.hocg.rabbit.ums.biz.pojo.ro.UserGroupCompleteRo;
import in.hocg.rabbit.ums.biz.pojo.ro.UserGroupPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    IPage<UserGroup> paging(@Param("ro") UserGroupPagingRo ro, @Param("ofPage") Page ofPage);

    IPage<UserGroup> complete(@Param("ro") UserGroupCompleteRo ro, @Param("ofPage") Page ofPage);
}
