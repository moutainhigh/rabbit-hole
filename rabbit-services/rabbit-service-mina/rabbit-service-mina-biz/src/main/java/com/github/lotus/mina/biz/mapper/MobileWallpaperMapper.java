package com.github.lotus.mina.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mina.biz.entity.MobileWallpaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
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
}
