package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.StatusMaterial;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaStatusMaterialComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/2/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface StatusMaterialMapping {
    MinaStatusMaterialComplexVo asMinaComplex(StatusMaterial entity);
}
