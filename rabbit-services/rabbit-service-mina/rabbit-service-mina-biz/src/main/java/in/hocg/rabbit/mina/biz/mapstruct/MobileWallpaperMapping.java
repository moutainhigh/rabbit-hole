package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.MobileWallpaper;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaMobileWallpaperComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface MobileWallpaperMapping {
    @Mapping(target = "creatorName", ignore = true)
    MinaMobileWallpaperComplexVo asComplex(MobileWallpaper entity);
}
