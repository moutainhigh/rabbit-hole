package com.github.lotus.chaos.biz.modules.lang.service;

import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperCompleteRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.WallpaperPagingRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.WallpaperComplexVo;

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
}
