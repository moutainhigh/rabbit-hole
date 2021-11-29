package in.hocg.rabbit.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.biz.entity.ShortUrl;
import in.hocg.rabbit.com.biz.pojo.ro.ShortUrlInsertRo;
import in.hocg.rabbit.com.biz.pojo.ro.ShortUrlPagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.ShortUrlUpdateRo;
import in.hocg.rabbit.com.biz.pojo.vo.ShortUrlComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [基础模块] 短链接表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-14
 */
public interface ShortUrlService extends AbstractService<ShortUrl> {

    String getOrCreateShortUrlCode(String originalUrl, Long userId);

    Optional<ShortUrl> getByCode(String code);

    IPage<ShortUrlComplexVo> paging(ShortUrlPagingRo ro);

    void insertOne(ShortUrlInsertRo ro);

    void updateOne(Long id, ShortUrlUpdateRo ro);
}
