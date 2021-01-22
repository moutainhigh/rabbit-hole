package com.github.lotus.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.GameCard;
import com.github.lotus.mina.biz.mapper.GameCardMapper;
import com.github.lotus.mina.biz.mapstruct.GameCardMapping;
import com.github.lotus.mina.biz.pojo.ro.GameCardPageRo;
import com.github.lotus.mina.biz.pojo.vo.GameComplexVo;
import com.github.lotus.mina.biz.service.GameCardService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.utils.Enabled;
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
public class GameCardServiceImpl extends AbstractServiceImpl<GameCardMapper, GameCard>
    implements GameCardService {
    private final GameCardMapping mapping;

    @Override
    public IPage<GameComplexVo> paging(GameCardPageRo ro) {
        ro.setEnabled(Enabled.On.getCode());
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convert);
    }

    private GameComplexVo convert(GameCard entity) {
        GameComplexVo result = mapping.asGameComplexVo(entity);
        String tags = entity.getTags();

        if (Strings.isNotBlank(tags)) {
            result.setTags(Lists.newArrayList(tags.split(";")));
        }

        return result;
    }
}
