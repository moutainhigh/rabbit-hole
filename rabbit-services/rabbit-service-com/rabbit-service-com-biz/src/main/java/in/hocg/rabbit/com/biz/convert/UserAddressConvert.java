package in.hocg.rabbit.com.biz.convert;

import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.com.biz.entity.UserAddress;
import in.hocg.rabbit.com.biz.mapstruct.UserAddressMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserAddressConvert {
    private final UserAddressMapping mapping;

    public UserAddressFeignVo asUserAddressFeignVo(UserAddress entity) {
        return mapping.asUserAddressFeignVo(entity);
    }
}
