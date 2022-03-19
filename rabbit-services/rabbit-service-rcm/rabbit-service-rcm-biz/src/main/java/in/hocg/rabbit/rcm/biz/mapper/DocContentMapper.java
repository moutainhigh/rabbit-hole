package in.hocg.rabbit.rcm.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [内容模块] 文档内容表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Mapper
public interface DocContentMapper extends BaseMapper<DocContent> {

}
