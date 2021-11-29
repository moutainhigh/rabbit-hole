package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.UserIntegralFlow;
import in.hocg.rabbit.com.biz.pojo.vo.MinaIntegralFlowVo;
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
