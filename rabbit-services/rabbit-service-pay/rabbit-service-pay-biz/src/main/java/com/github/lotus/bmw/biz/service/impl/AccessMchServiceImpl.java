package com.github.lotus.bmw.biz.service.impl;

import com.github.lotus.bmw.biz.entity.AccessMch;
import com.github.lotus.bmw.biz.mapper.AccessMchMapper;
import com.github.lotus.bmw.biz.service.AccessMchService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 接入商户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccessMchServiceImpl extends AbstractServiceImpl<AccessMchMapper, AccessMch>
    implements AccessMchService {

    @Override
    public Optional<AccessMch> getByEncoding(String encoding) {
        return lambdaQuery().eq(AccessMch::getEncoding, encoding).oneOpt();
    }

    @Override
    public boolean checkSupportPayType(Long accessMchId, String payType) {
        return Objects.nonNull(baseMapper.checkSupportPayType(accessMchId, payType));
    }
}
