package com.github.lotus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.ShortUrl;
import com.github.lotus.com.biz.mapper.ShortUrlMapper;
import com.github.lotus.com.biz.mapstruct.ShortUrlMapping;
import com.github.lotus.com.biz.pojo.ro.ShortUrlInsertRo;
import com.github.lotus.com.biz.pojo.ro.ShortUrlPagingRo;
import com.github.lotus.com.biz.pojo.ro.ShortUrlUpdateRo;
import com.github.lotus.com.biz.pojo.vo.ShortUrlComplexVo;
import com.github.lotus.com.biz.service.ShortUrlService;
import com.github.lotus.com.biz.utils.ShortUrlUtils;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.utils.Enabled;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * [基础模块] 短链接表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShortUrlServiceImpl extends AbstractServiceImpl<ShortUrlMapper, ShortUrl>
    implements ShortUrlService {
    private final ShortUrlMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String getOrCreateShortUrlCode(String originalUrl, Long userId) {
        LocalDateTime now = LocalDateTime.now();

        final Optional<ShortUrl> shortUrlOpt = selectOneByOriginalUrl(originalUrl);
        if (shortUrlOpt.isPresent()) {
            return shortUrlOpt.get().getCode();
        }
        final ShortUrl entity = new ShortUrl();
        entity.setCode("tmp");
        entity.setOriginalUrl(originalUrl);
        entity.setCreatedAt(now);
        entity.setCreator(userId);
        entity.setEnabled(Enabled.On.getCodeStr());
        validInsert(entity);

        final Long id = entity.getId();
        final String code = ShortUrlUtils.getShortCode(id);

        final ShortUrl update = new ShortUrl();
        update.setId(id);
        update.setCode(code);
        validUpdateById(update);
        return code;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<ShortUrl> getByCode(String code) {
        return lambdaQuery().eq(ShortUrl::getCode, code).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<ShortUrlComplexVo> paging(ShortUrlPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(ShortUrlInsertRo ro) {
        Long userId = ro.getUserId();
        String originalUrl = ro.getOriginalUrl();
        String enabled = ro.getEnabled();

        final String code = getOrCreateShortUrlCodeUseRetry(originalUrl, userId, 3);
        final Optional<ShortUrl> shortUrlOpt = getByCode(code);
        if (!shortUrlOpt.isPresent()) {
            return;
        }
        final ShortUrl shortUrl = shortUrlOpt.get();
        if (LangUtils.equals(shortUrl.getEnabled(), enabled)) {
            return;
        }

        final ShortUrl update = new ShortUrl();
        update.setId(shortUrl.getId());
        update.setEnabled(enabled);
        validUpdateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, ShortUrlUpdateRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        ShortUrl entity = mapping.asShortUrl(ro);
        entity.setId(id);
        entity.setLastUpdater(userId);
        entity.setLastUpdatedAt(now);
        validUpdateById(entity);
    }

    private String getOrCreateShortUrlCodeUseRetry(String originalUrl, Long userId, int retry) {
        try {
            return ((ShortUrlService) AopContext.currentProxy()).getOrCreateShortUrlCode(originalUrl, userId);
        } catch (DuplicateKeyException e) {
            if (retry <= 0) {
                throw ServiceException.wrap("系统繁忙，请稍后");
            }
            return getOrCreateShortUrlCodeUseRetry(originalUrl, userId, --retry);
        }
    }

    private ShortUrlComplexVo convertComplex(ShortUrl entity) {
        return mapping.asShortUrlComplexVo(entity);
    }

    private Optional<ShortUrl> selectOneByOriginalUrl(String originalUrl) {
        return lambdaQuery().eq(ShortUrl::getOriginalUrl, originalUrl).oneOpt();
    }
}
