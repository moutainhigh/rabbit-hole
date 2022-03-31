package in.hocg.rabbit.mina.biz.convert;

import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import in.hocg.rabbit.mina.biz.mapstruct.Y2bChannelMapping;
import in.hocg.rabbit.mina.biz.pojo.vo.Y2bChannelCompleteVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/3/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class Y2bChannelConvert {
    private final Y2bChannelMapping mapping;
    public Y2bChannelCompleteVo asCompleteVo(Y2bChannel entity) {
        return mapping.asY2bChannelCompleteVo(entity);
    }
}
