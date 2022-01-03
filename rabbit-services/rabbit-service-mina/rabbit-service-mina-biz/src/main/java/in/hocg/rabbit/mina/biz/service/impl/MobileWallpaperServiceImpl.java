package in.hocg.rabbit.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.MobileWallpaper;
import in.hocg.rabbit.mina.biz.mapper.MobileWallpaperMapper;
import in.hocg.rabbit.mina.biz.mapstruct.MobileWallpaperMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperTagsPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaMobileWallpaperComplexVo;
import in.hocg.rabbit.mina.biz.service.MobileWallpaperService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
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
        ro.setEnabled(true);
        return baseMapper.paging(ro, ro.ofPage()).convert(this::complex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MinaMobileWallpaperComplexVo> randomComplete(MinaMobileWallpaperCompleteRo ro) {
        ro.setEnabled(true);
        return baseMapper.randomComplete(ro, ro.ofPage()).convert(this::complex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<String> pagingByTags(MinaMobileWallpaperTagsPagingRo ro) {
        return baseMapper.pagingByTags(ro, ro.ofPage());
    }

    private MinaMobileWallpaperComplexVo complex(MobileWallpaper entity) {
        return mapping.asComplex(entity);
    }
}
