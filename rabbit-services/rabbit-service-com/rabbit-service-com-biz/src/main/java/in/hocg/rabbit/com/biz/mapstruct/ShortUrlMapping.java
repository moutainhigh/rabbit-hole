package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.ShortUrl;
import in.hocg.rabbit.com.biz.pojo.ro.ShortUrlUpdateRo;
import in.hocg.rabbit.com.biz.pojo.vo.ShortUrlComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/4/5.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ShortUrlMapping {

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "creator", ignore = true)
    ShortUrl asShortUrl(ShortUrlUpdateRo qo);

    @Mapping(target = "creatorName", ignore = true)
    ShortUrlComplexVo asShortUrlComplexVo(ShortUrl entity);
}
