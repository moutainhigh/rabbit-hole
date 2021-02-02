package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.pojo.ro.AccessAppInsertRo;
import com.github.lotus.pay.biz.pojo.vo.AccessAppComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccessAppMapping {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdIp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AccessApp asAccessApp(AccessAppInsertRo ro);

    AccessAppComplexVo asComplex(AccessApp entity);
}
