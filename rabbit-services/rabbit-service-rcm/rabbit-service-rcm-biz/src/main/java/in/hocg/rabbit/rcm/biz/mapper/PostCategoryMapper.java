package in.hocg.rabbit.rcm.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCategoryCompleteRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostCategoryOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [内容模块] 帖文类目表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Mapper
public interface PostCategoryMapper extends BaseMapper<PostCategory> {

    IPage<PostCategory> complete(@Param("ro") PostCategoryCompleteRo ro, Page ofPage);
}
