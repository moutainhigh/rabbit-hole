package com.github.lotus.chaos.biz.modules.lang.service.impl;

import com.github.lotus.chaos.biz.modules.lang.constant.ChaosCacheKeys;
import com.github.lotus.chaos.biz.modules.lang.manager.LangManager;
import com.github.lotus.chaos.biz.modules.lang.manager.UnsplashManager;
import com.github.lotus.chaos.biz.modules.lang.mapstruct.WallpaperMapping;
import com.github.lotus.chaos.biz.modules.lang.pojo.dto.UnsplashPagingDto;
import com.github.lotus.chaos.biz.modules.lang.pojo.dto.UnsplashPhotoDto;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperCompleteRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.WallpaperComplexVo;
import com.github.lotus.chaos.biz.modules.lang.service.HttpService;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class HttpServiceImpl implements HttpService {
    private final UnsplashManager unsplashManager;
    private final LangManager langManager;
    private final WallpaperMapping mapping;

    @Override
    @Cacheable(cacheNames = ChaosCacheKeys.PAGING_WALLPAPER, key = "#ro.page")
    public List<WallpaperComplexVo> pagingWallpaper(WallpaperPagingRo ro) {
        List<UnsplashPhotoDto> result = unsplashManager.paging(ro.getPage(), ro.getSize());
        return LangUtils.toList(result, mapping::asWallpaperComplexVo);
    }

    @Override
    public List<WallpaperComplexVo> completeWallpaper(WallpaperCompleteRo ro) {
        String keyword = ro.getKeyword();
        int size = ro.getSize();
        UnsplashPagingDto result = unsplashManager.search(keyword, null, size);
        return LangUtils.toList(result.getResults(), mapping::asWallpaperComplexVo);
    }

    @Override
    @Cacheable(cacheNames = ChaosCacheKeys.GUMBALLS_GIFT, key = "#day")
    public String getGumballsGift(String day) {
        return langManager.getGumballsGift();
    }

}
