package in.hocg.rabbit.mina.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mina.biz.entity.MobileWallpaper;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperTagsPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [模块] 手机壁纸表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-03-13
 */
@Mapper
public interface MobileWallpaperMapper extends BaseMapper<MobileWallpaper> {

    IPage<MobileWallpaper> paging(@Param("ro") MinaMobileWallpaperPagingRo ro, @Param("ofPage") Page ofPage);

    IPage<String> pagingByTags(@Param("ro") MinaMobileWallpaperTagsPagingRo ro, @Param("ofPage") Page ofPage);

    IPage<MobileWallpaper> randomComplete(@Param("ro") MinaMobileWallpaperCompleteRo ro, @Param("ofPage") Page ofPage);
}
