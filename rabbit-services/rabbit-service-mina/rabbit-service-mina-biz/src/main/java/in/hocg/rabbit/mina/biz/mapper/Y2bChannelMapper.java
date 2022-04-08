package in.hocg.rabbit.mina.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelPageRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [YouTube] YouTube频道表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-03-30
 */
@Mapper
public interface Y2bChannelMapper extends BaseMapper<Y2bChannel> {

    IPage<Y2bChannel> complete(@Param("ro") YouTubeChannelCompleteRo ro, Page<Object> ofPage);

    IPage<Y2bChannel> paging(@Param("ro") YouTubeChannelPageRo ro, Page<Object> ofPage);
}
