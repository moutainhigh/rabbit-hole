package com.github.lotus.ums.biz.service;

import com.github.lotus.ums.biz.entity.UserAddress;
import com.github.lotus.ums.biz.pojo.vo.UserAddressComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

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
