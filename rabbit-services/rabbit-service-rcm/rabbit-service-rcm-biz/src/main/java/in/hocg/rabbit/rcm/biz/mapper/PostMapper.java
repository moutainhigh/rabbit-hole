package in.hocg.rabbit.rcm.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.rcm.biz.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostScrollRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [内容模块] 帖文表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    IPage<Post> scroll(@Param("ro") PostScrollRo ro, Page<Object> ofPage);
}
