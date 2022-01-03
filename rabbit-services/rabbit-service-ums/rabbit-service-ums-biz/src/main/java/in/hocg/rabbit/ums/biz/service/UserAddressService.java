package in.hocg.rabbit.ums.biz.service;

import in.hocg.rabbit.ums.biz.entity.UserAddress;
import in.hocg.rabbit.ums.biz.pojo.vo.UserAddressComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 收货地址表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-22
 */
public interface UserAddressService extends AbstractService<UserAddress> {

    Optional<UserAddressComplexVo> getDefaultByUserId(Long userId);
}
