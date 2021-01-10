package com.github.lotus.chaos.biz.mapstruct;

import com.github.lotus.chaos.biz.pojo.dto.UnsplashPhotoDto;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface WallpaperMapping {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "remark", source = "dto.altDescription")
    @Mapping(target = "url", source = "dto.urls.small")
    @Mapping(target = "rawUrl", source = "dto.urls.raw")
    @Mapping(target = "fullUrl", source = "dto.urls.full")
    @Mapping(target = "title", source = "dto.description")
    @Mapping(target = "author.nickname", source = "dto.user.name")
    @Mapping(target = "author.avatarUrl", source = "dto.user.profileImage.large")
    WallpaperComplexVo asWallpaperComplexVo(UnsplashPhotoDto dto);
}
