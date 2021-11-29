package in.hocg.rabbit.chaos.biz.mapstruct;

import in.hocg.rabbit.chaos.biz.pojo.dto.UnsplashPhotoDto;
import in.hocg.rabbit.chaos.biz.pojo.vo.WallpaperComplexVo;
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

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "remark", source = "altDescription")
    @Mapping(target = "url", source = "urls.small")
    @Mapping(target = "rawUrl", source = "urls.raw")
    @Mapping(target = "fullUrl", source = "urls.full")
    @Mapping(target = "title", source = "description")
    @Mapping(target = "author.nickname", source = "user.name")
    @Mapping(target = "author.avatarUrl", source = "user.profileImage.large")
    WallpaperComplexVo asWallpaperComplexVo(UnsplashPhotoDto dto);
}
