package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import in.hocg.rabbit.mina.biz.pojo.vo.Y2bChannelCompleteVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2022/3/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface Y2bChannelMapping {
    Y2bChannelCompleteVo asY2bChannelCompleteVo(Y2bChannel entity);
}
