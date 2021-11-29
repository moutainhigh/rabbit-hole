package in.hocg.rabbit.mina.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mina.biz.entity.GameCard;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCardPagingRo;
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
public interface GameCardMapper extends BaseMapper<GameCard> {

    IPage<GameCard> pagingForMina(@Param("ro") MinaGameCardPagingRo ro, @Param("ofPage") Page<?> ofPage);

    IPage<GameCard> complete(@Param("ro") GameCardCompleteRo ro, @Param("ofPage") Page ofPage);

    IPage<GameCard> paging(@Param("ro") GameCardPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
