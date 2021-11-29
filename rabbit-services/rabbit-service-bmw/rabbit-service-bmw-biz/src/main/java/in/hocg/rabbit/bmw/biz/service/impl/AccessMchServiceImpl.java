package in.hocg.rabbit.bmw.biz.service.impl;

import in.hocg.rabbit.bmw.api.pojo.vo.AccessMchComplexVo;
import in.hocg.rabbit.bmw.biz.entity.AccessMch;
import in.hocg.rabbit.bmw.biz.mapper.AccessMchMapper;
import in.hocg.rabbit.bmw.biz.mapstruct.AccessMchMapping;
import in.hocg.rabbit.bmw.biz.service.AccessMchService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
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
    private final AccessMchMapping mapping;

    @Override
    public Optional<AccessMch> getByEncoding(String encoding) {
        return lambdaQuery().eq(AccessMch::getEncoding, encoding).oneOpt();
    }

    @Override
    public boolean checkSupportPayType(Long accessMchId, String payType) {
        return Objects.nonNull(baseMapper.checkSupportPayType(accessMchId, payType));
    }

    @Override
    public List<AccessMchComplexVo> listComplexById(Collection<Long> id) {
        return LangUtils.toList(listByIds(id), this::convertComplex);
    }

    private AccessMchComplexVo convertComplex(AccessMch entity) {
        return mapping.asAccessMchComplexVo(entity);
    }
}
