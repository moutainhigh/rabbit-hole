package com.github.lotus.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.AppCard;
import com.github.lotus.mina.biz.mapper.AppCardMapper;
import com.github.lotus.mina.biz.mapstruct.AppCardMapping;
import com.github.lotus.mina.biz.pojo.ro.AppCardPageRo;
import com.github.lotus.mina.biz.pojo.vo.AppComplexVo;
import com.github.lotus.mina.biz.service.AppCardService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [小程序模块] 游戏表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AppCardServiceImpl extends AbstractServiceImpl<AppCardMapper, AppCard>
    implements AppCardService {
    private final AppCardMapping mapping;

    @Override
    public IPage<AppComplexVo> paging(AppCardPageRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convert);
    }

    private AppComplexVo convert(AppCard entity) {
        AppComplexVo result = mapping.asAppComplexVo(entity);
        String tags = entity.getTags();
        String pageUrl = entity.getPageUrl();

        if (Strings.isNotBlank(tags)) {
            result.setTags(Lists.newArrayList(tags.split(";")));
        }

        AppComplexVo.Href href = new AppComplexVo.Href();
        href.setMini(new AppComplexVo.Href.Mini().setPath(pageUrl));
        result.setHref(href);

        return result;
    }
}
