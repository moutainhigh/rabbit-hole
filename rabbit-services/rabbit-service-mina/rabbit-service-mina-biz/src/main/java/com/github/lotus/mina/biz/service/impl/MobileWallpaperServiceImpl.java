package com.github.lotus.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.MobileWallpaper;
import com.github.lotus.mina.biz.mapper.MobileWallpaperMapper;
import com.github.lotus.mina.biz.mapstruct.MobileWallpaperMapping;
import com.github.lotus.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
import com.github.lotus.mina.biz.pojo.vo.MinaMobileWallpaperComplexVo;
import com.github.lotus.mina.biz.service.MobileWallpaperService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MobileWallpaperServiceImpl extends AbstractServiceImpl<MobileWallpaperMapper, MobileWallpaper>
    implements MobileWallpaperService {
    private final MobileWallpaperMapping mapping;

    @Override
    public Boolean hasByFileHash(String hash) {
        return has(MobileWallpaper::getFileHash, hash, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MinaMobileWallpaperComplexVo> paging(MinaMobileWallpaperPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::complex);
    }

    private MinaMobileWallpaperComplexVo complex(MobileWallpaper entity) {
        return mapping.asComplex(entity);
    }
}
