package com.github.lotus.bmw.biz.mapstruct;

import com.github.lotus.bmw.api.pojo.vo.AccessMchComplexVo;
import com.github.lotus.bmw.biz.entity.AccessMch;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccessMchMapping {

    AccessMchComplexVo asAccessMchComplexVo(AccessMch entity);
}
