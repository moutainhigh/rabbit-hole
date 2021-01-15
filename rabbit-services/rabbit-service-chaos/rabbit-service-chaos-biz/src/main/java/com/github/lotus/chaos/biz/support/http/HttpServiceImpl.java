package com.github.lotus.chaos.biz.support.http;

import com.github.lotus.chaos.biz.constant.ChaosCacheKeys;
import com.github.lotus.chaos.biz.manager.LangManager;
import com.github.lotus.chaos.biz.manager.UnsplashManager;
import com.github.lotus.chaos.biz.mapstruct.WallpaperMapping;
import com.github.lotus.chaos.biz.pojo.dto.UnsplashPagingDto;
import com.github.lotus.chaos.biz.pojo.dto.UnsplashPhotoDto;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperCompleteRo;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperTopicPagingRo;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperComplexVo;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.KeyValue;
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
    @Cacheable(cacheNames = ChaosCacheKeys.PAGING_TOPIC_WALLPAPER, key = "#topicId + #ro.page + #ro.size")
    public List<WallpaperComplexVo> pagingByTopic(String topicId, WallpaperTopicPagingRo ro) {
        List<UnsplashPhotoDto> result = unsplashManager.pagingByTopic(topicId, ro.getPage(), ro.getSize());
        return LangUtils.toList(result, mapping::asWallpaperComplexVo);
    }

    @Override
    public WallpaperComplexVo random() {
        UnsplashPhotoDto result = unsplashManager.random();
        return mapping.asWallpaperComplexVo(result);
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

    @Override
    public List<KeyValue> getTopic() {
        return unsplashManager.topics();
    }

}
