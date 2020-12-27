package com.github.lotus.chaos.biz.modules.lang.mapstruct;

import com.github.lotus.chaos.biz.modules.lang.pojo.dto.UnsplashPagingDto;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.WallpaperComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface WallpaperMapping {

    WallpaperComplexVo asWallpaperComplexVo(UnsplashPagingDto dto);
}
