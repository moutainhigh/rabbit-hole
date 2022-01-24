package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.StatusMaterial;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaStatusMaterialPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaStatusMaterialComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

/**
 * <p>
 * [小程序模块] 状态素材表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-02-05
 */
public interface StatusMaterialService extends AbstractService<StatusMaterial> {

    IPage<MinaStatusMaterialComplexVo> pagingForMina(MinaStatusMaterialPagingRo ro);
}
