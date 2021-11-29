package in.hocg.rabbit.bmw.biz.mapstruct;

import in.hocg.rabbit.bmw.biz.entity.PaySceneSupport;
import in.hocg.rabbit.bmw.biz.pojo.vo.PaySceneItemVo;
import org.mapstruct.Mapper;

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
