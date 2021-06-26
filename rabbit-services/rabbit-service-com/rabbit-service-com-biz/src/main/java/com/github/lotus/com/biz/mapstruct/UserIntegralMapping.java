package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.UserIntegral;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralStatsVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserIntegralMapping {

   default MinaIntegralStatsVo asMinaIntegralStatsVo(UserIntegral entity) {
       MinaIntegralStatsVo result = new MinaIntegralStatsVo();
       result.setUsed(entity.getUsedIntegral());
       result.setAvailable(entity.getAvlIntegral());
       result.setTotal(entity.getUsedIntegral().add(entity.getAvlIntegral()));
       return result;
   }
}
