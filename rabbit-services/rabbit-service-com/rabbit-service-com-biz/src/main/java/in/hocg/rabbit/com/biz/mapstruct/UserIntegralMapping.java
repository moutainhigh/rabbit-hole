package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.UserIntegral;
import in.hocg.rabbit.com.biz.pojo.vo.MinaIntegralStatsVo;
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
       result.setAvailable(entity.getAvailIntegral());
       result.setTotal(entity.getUsedIntegral().add(entity.getAvailIntegral()));
       return result;
   }
}
