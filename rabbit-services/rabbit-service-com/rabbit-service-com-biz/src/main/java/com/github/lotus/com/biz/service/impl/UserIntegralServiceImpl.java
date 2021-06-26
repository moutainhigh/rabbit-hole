package com.github.lotus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.UserIntegral;
import com.github.lotus.com.biz.mapper.UserIntegralMapper;
import com.github.lotus.com.biz.mapstruct.UserIntegralMapping;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralStatsVo;
import com.github.lotus.com.biz.service.UserIntegralFlowService;
import com.github.lotus.com.biz.service.UserIntegralService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * [通用] 用户积分表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserIntegralServiceImpl extends AbstractServiceImpl<UserIntegralMapper, UserIntegral> implements UserIntegralService {
    private final UserIntegralMapping mapping;
    private final UserIntegralFlowService userIntegralFlowService;

    @Override
    public MinaIntegralStatsVo getStats(Long userId) {
        return getByUserId(userId).map(mapping::asMinaIntegralStatsVo).orElse(null);
    }

    @Override
    public IPage<MinaIntegralFlowVo> pageFlow(MinaIntegralFlowPageRo ro) {
        return userIntegralFlowService.pageFlow(ro);
    }

    private Optional<UserIntegral> getByUserId(Long userId) {
        return lambdaQuery().eq(UserIntegral::getUserId, userId).oneOpt();
    }
}
