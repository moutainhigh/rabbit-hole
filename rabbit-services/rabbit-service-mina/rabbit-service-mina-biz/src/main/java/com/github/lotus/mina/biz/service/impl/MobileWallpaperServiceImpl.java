package com.github.lotus.mina.biz.service.impl;

import com.github.lotus.mina.biz.entity.MobileWallpaper;
import com.github.lotus.mina.biz.mapper.MobileWallpaperMapper;
import com.github.lotus.mina.biz.service.MobileWallpaperService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [模块] 手机壁纸表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MobileWallpaperServiceImpl extends AbstractServiceImpl<MobileWallpaperMapper, MobileWallpaper> implements MobileWallpaperService {

    @Override
    public Boolean hasByFileHash(String hash) {
        return has(MobileWallpaper::getFileHash, hash, null);
    }
}
