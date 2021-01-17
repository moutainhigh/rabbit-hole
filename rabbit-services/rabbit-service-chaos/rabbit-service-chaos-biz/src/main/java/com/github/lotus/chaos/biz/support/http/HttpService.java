package com.github.lotus.chaos.biz.support.http;

import com.github.lotus.chaos.biz.pojo.ro.WallpaperCompleteRo;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.pojo.ro.WallpaperTopicPagingRo;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperComplexVo;
import com.github.lotus.chaos.biz.pojo.vo.WallpaperTopicVo;

import java.util.List;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface HttpService {

    List<WallpaperComplexVo> pagingWallpaper(WallpaperPagingRo ro);

    List<WallpaperComplexVo> completeWallpaper(WallpaperCompleteRo ro);

    String getGumballsGift(String day);

    List<WallpaperTopicVo> getTopic();

    List<WallpaperComplexVo> pagingByTopic(String topicId, WallpaperTopicPagingRo ro);

    WallpaperComplexVo random();
}
