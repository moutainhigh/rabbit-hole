package in.hocg.rabbit.mina.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.StatusMaterial;
import in.hocg.rabbit.mina.biz.mapper.StatusMaterialMapper;
import in.hocg.rabbit.mina.biz.mapstruct.StatusMaterialMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaStatusMaterialPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaStatusMaterialComplexVo;
import in.hocg.rabbit.mina.biz.service.StatusMaterialService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * [小程序模块] 状态素材表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-02-05
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StatusMaterialServiceImpl extends AbstractServiceImpl<StatusMaterialMapper, StatusMaterial> implements StatusMaterialService {
    private final StatusMaterialMapping mapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<MinaStatusMaterialComplexVo> pagingForMina(MinaStatusMaterialPagingRo ro) {
        return baseMapper.pagingForMina(ro, ro.ofPage()).convert(this::convertMinaComplex);
    }

    private MinaStatusMaterialComplexVo convertMinaComplex(StatusMaterial entity) {
        return mapping.asMinaComplex(entity);
    }
}
