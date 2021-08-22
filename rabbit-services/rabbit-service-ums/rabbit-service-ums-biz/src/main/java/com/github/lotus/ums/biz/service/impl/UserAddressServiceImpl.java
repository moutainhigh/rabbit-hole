package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.UserAddress;
import com.github.lotus.ums.biz.mapper.UserAddressMapper;
import com.github.lotus.ums.biz.mapstruct.UserAddressMapping;
import com.github.lotus.ums.biz.pojo.vo.UserAddressComplexVo;
import com.github.lotus.ums.biz.service.UserAddressService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 收货地址表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-22
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserAddressServiceImpl extends AbstractServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
    private final UserAddressMapping mapping;

    @Override
    public Optional<UserAddressComplexVo> getDefaultByUserId(Long userId) {
        return baseMapper.getDefaultByUserId(userId).map(this::convertComplex);
    }

    private UserAddressComplexVo convertComplex(UserAddress entity) {
        return mapping.asUserAddressComplexVo(entity);
    }
}
