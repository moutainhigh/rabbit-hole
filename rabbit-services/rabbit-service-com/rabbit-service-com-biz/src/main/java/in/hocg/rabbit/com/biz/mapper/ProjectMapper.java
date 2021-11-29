package in.hocg.rabbit.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.com.biz.entity.Project;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectCompleteRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [通用模块] 项目表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    IPage<Project> complete(@Param("ro") ProjectCompleteRo ro, @Param("page") Page page);

    IPage<Project> paging(@Param("ro") ProjectPagingRo ro, @Param("page") Page page);
}
