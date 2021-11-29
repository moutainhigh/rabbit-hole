package in.hocg.rabbit.chaos.biz.support.http;

import in.hocg.rabbit.chaos.biz.constant.CacheKeys;
import in.hocg.rabbit.chaos.biz.manager.LangManager;
import in.hocg.rabbit.chaos.biz.manager.UnsplashManager;
import in.hocg.rabbit.chaos.biz.mapstruct.WallpaperMapping;
import in.hocg.rabbit.chaos.biz.pojo.dto.UnsplashPagingDto;
import in.hocg.rabbit.chaos.biz.pojo.dto.UnsplashPhotoDto;
import in.hocg.rabbit.chaos.biz.pojo.ro.UploadMiStepRo;
import in.hocg.rabbit.chaos.biz.pojo.ro.WallpaperCompleteRo;
import in.hocg.rabbit.chaos.biz.pojo.ro.WallpaperPagingRo;
import in.hocg.rabbit.chaos.biz.pojo.ro.WallpaperTopicPagingRo;
import in.hocg.rabbit.chaos.biz.pojo.vo.WallpaperComplexVo;
import in.hocg.rabbit.chaos.biz.pojo.vo.WallpaperTopicVo;
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
    @Cacheable(cacheNames = CacheKeys.PAGING_WALLPAPER, key = "#ro.page", unless = "#result == null")
    public List<WallpaperComplexVo> pagingWallpaper(WallpaperPagingRo ro) {
        List<UnsplashPhotoDto> result = unsplashManager.paging(ro.getPage(), ro.getSize());
        return LangUtils.toList(result, mapping::asWallpaperComplexVo);
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.PAGING_TOPIC_WALLPAPER, key = "#topicId + #ro.page + #ro.size", unless = "#result == null")
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
    public void uploadMiStep(UploadMiStepRo ro) {
        String username = ro.getUsername();
        String password = ro.getPassword();
        Integer count = ro.getCount();
        langManager.uploadMiStep(username, password, count);
    }

    @Override
    public List<WallpaperComplexVo> completeWallpaper(WallpaperCompleteRo ro) {
        String keyword = ro.getKeyword();
        int size = ro.getSize();
        UnsplashPagingDto result = unsplashManager.search(keyword, null, size);
        return LangUtils.toList(result.getResults(), mapping::asWallpaperComplexVo);
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.GUMBALLS_GIFT, key = "#day", unless = "#result == null")
    public String getGumballsGift(String day) {
        return langManager.getGumballsGift();
    }

    @Override
    public List<WallpaperTopicVo> getTopic() {
        return unsplashManager.topics();
    }

}
