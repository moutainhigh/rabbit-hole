package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PayScene;
import com.github.lotus.bmw.biz.mapper.PaySceneMapper;
import com.github.lotus.bmw.biz.service.PaySceneService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付模块] 支付场景表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaySceneServiceImpl extends AbstractServiceImpl<PaySceneMapper, PayScene> implements PaySceneService {

}
