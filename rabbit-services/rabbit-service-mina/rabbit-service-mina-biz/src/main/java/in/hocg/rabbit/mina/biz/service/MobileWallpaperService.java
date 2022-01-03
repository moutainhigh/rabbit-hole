package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.MobileWallpaper;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaMobileWallpaperTagsPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaMobileWallpaperComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

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

    IPage<MinaMobileWallpaperComplexVo> paging(MinaMobileWallpaperPagingRo ro);

    IPage<String> pagingByTags(MinaMobileWallpaperTagsPagingRo ro);

    IPage<MinaMobileWallpaperComplexVo> randomComplete(MinaMobileWallpaperCompleteRo ro);
}
