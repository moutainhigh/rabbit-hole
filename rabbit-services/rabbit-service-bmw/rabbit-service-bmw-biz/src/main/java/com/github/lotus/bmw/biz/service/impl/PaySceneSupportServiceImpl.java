package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PaySceneSupport;
import com.github.lotus.bmw.biz.mapper.PaySceneSupportMapper;
import com.github.lotus.bmw.biz.service.PaySceneSupportService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [支付模块] 支付场景支持的支付类型 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaySceneSupportServiceImpl extends AbstractServiceImpl<PaySceneSupportMapper, PaySceneSupport> implements PaySceneSupportService {

    @Override
    public List<PaySceneSupport> listByPaySceneId(Long paySceneId) {
        return lambdaQuery().eq(PaySceneSupport::getPaySceneId, paySceneId).list();
    }
}
