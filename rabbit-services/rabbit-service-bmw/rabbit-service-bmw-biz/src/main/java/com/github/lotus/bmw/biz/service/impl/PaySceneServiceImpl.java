package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.PayScene;
import com.github.lotus.bmw.biz.entity.PaySceneSupport;
import com.github.lotus.bmw.biz.mapper.PaySceneMapper;
import com.github.lotus.bmw.biz.mapstruct.PaySceneMapping;
import com.github.lotus.bmw.biz.pojo.vo.PaySceneItemVo;
import com.github.lotus.bmw.biz.service.PaySceneService;
import com.github.lotus.bmw.biz.service.PaySceneSupportService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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
    private final PaySceneMapping mapping;
    private final PaySceneSupportService paySceneSupportService;

    @Override
    public Optional<PayScene> getByAccessMchIdAndEncoding(Long accessMchId, String sceneCode) {
        return this.lambdaQuery().eq(PayScene::getAccessMchId, accessMchId)
            .eq(PayScene::getEncoding, sceneCode).oneOpt();
    }

    @Override
    public List<PaySceneItemVo> listBySceneCodeAndAccessMchId(String sceneCode, Long accessMchId) {
        return LangUtils.toList(this.listByEncodingAndAccessMchId(sceneCode, accessMchId), this::convertPaySceneItemVo);
    }

    @Override
    public List<PaySceneItemVo> listByPaySceneId(Long paySceneId) {
        return LangUtils.toList(paySceneSupportService.listByPaySceneId(paySceneId), this::convertPaySceneItemVo);
    }

    private PaySceneItemVo convertPaySceneItemVo(PaySceneSupport entity) {
        return mapping.asPaySceneItemVo(entity);
    }

    private List<PaySceneSupport> listByEncodingAndAccessMchId(String sceneCode, Long accessMchId) {
        return baseMapper.listByEncodingAndAccessMchId(sceneCode, accessMchId);
    }

}
