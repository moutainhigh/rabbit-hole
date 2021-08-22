package com.github.lotus.ums.biz.mapper;

import com.github.lotus.ums.biz.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.io.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 收货地址表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-22
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {

    Optional<UserAddress> getDefaultByUserId(@Param("userId") Long userId);
}
