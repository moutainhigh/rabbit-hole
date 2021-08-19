package com.github.lotus.bmw.biz.mapstruct;

import com.github.lotus.bmw.biz.entity.PaySceneSupport;
import com.github.lotus.bmw.biz.pojo.vo.PaySceneItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PaySceneMapping {

    PaySceneItemVo asPaySceneItemVo(PaySceneSupport entity);
}
