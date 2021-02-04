package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.StatusMaterial;
import com.github.lotus.mina.biz.pojo.vo.MinaStatusMaterialComplexVo;
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
