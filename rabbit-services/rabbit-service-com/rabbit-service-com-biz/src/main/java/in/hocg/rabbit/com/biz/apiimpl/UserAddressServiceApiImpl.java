package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.UserAddressServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.com.biz.convert.UserAddressConvert;
import in.hocg.rabbit.com.biz.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserAddressServiceApiImpl implements UserAddressServiceApi {
    private final UserAddressService service;
    private final UserAddressConvert convert;

    @Override
    public UserAddressFeignVo getDefaultByUserIdAndType(Long userId, String type) {
        return convert.asUserAddressFeignVo(service.getDefaultByUserIdAndType(userId, type));
    }

    @Override
    public UserAddressFeignVo getById(Long id) {
        return convert.asUserAddressFeignVo(service.getById(id));
    }
}
