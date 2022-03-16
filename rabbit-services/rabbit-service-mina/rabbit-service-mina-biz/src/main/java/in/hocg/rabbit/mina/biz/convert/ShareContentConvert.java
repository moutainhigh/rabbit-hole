package in.hocg.rabbit.mina.biz.convert;

import in.hocg.rabbit.mina.biz.entity.ShareContent;
import in.hocg.rabbit.mina.biz.mapstruct.ShareContentMapping;
import in.hocg.rabbit.mina.biz.pojo.vo.ShareContentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2022/3/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShareContentConvert {
    private final ShareContentMapping mapping;

    public ShareContentVo asShareContentVo(ShareContent entity) {
        return mapping.asShareContentVo(entity);
    }
}
