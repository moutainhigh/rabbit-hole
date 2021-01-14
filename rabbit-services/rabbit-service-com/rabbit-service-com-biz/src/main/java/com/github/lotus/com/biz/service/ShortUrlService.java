package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.ShortUrl;
import com.github.lotus.com.biz.pojo.ro.ShortUrlInsertRo;
import com.github.lotus.com.biz.pojo.ro.ShortUrlPagingRo;
import com.github.lotus.com.biz.pojo.ro.ShortUrlUpdateRo;
import com.github.lotus.com.biz.pojo.vo.ShortUrlComplexVo;
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
