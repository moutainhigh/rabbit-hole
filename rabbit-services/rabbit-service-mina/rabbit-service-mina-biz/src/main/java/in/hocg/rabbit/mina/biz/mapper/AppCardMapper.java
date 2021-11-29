package in.hocg.rabbit.mina.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mina.biz.entity.AppCard;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaAppCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [小程序模块] 游戏表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
@Mapper
public interface AppCardMapper extends BaseMapper<AppCard> {

    IPage<AppCard> pagingForMina(@Param("ro") MinaAppCardPagingRo ro, @Param("ofPage") Page ofPage);

    IPage<AppCard> paging(@Param("ro") AppCardPagingRo ro, @Param("ofPage") Page ofPage);

    IPage<AppCard> complete(@Param("ro") AppCardCompleteRo ro, @Param("ofPage") Page ofPage);
}
