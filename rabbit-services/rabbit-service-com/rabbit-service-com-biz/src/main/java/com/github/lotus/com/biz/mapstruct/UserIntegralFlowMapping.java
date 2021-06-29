package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.UserIntegralFlow;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserIntegralFlowMapping {

    MinaIntegralFlowVo asMinaIntegralFlowVo(UserIntegralFlow entity);
}
