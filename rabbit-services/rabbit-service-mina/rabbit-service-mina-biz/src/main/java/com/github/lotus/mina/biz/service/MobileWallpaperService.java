package com.github.lotus.mina.biz.service;

import com.github.lotus.mina.biz.entity.MobileWallpaper;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [模块] 手机壁纸表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-13
 */
public interface MobileWallpaperService extends AbstractService<MobileWallpaper> {

    Boolean hasByFileHash(String hash);
}
