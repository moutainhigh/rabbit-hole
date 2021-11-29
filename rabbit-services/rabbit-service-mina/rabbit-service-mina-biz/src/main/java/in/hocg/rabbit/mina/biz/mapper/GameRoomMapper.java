package in.hocg.rabbit.mina.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mina.biz.entity.GameRoom;
import in.hocg.rabbit.mina.biz.pojo.ro.RoomPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [小程序模块] 游戏房间表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-03-08
 */
@Mapper
public interface GameRoomMapper extends BaseMapper<GameRoom> {

    IPage<GameRoom> paging(@Param("ro") RoomPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
