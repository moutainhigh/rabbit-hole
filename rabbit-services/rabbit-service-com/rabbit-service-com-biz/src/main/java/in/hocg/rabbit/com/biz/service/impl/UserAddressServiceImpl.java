package in.hocg.rabbit.com.biz.service.impl;

import cn.hutool.core.util.PageUtil;
import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.com.biz.entity.District;
import in.hocg.rabbit.com.biz.entity.UserAddress;
import in.hocg.rabbit.com.biz.mapper.UserAddressMapper;
import in.hocg.rabbit.com.biz.mapstruct.UserAddressMapping;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientSaveRo;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientScrollRo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientOrdinaryVo;
import in.hocg.rabbit.com.biz.service.DistrictService;
import in.hocg.rabbit.com.biz.service.UserAddressService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * <p>
 * [用户模块] 物流地址表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserAddressServiceImpl extends AbstractServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
    private final UserAddressMapping mapping;
    private final DistrictService districtService;

    @Override
    public void insertOne(UserAddressClientSaveRo ro) {
        updateOne(null, ro);
    }

    @Override
    public void updateOne(Long id, UserAddressClientSaveRo ro) {
        UserAddress entity = mapping.asUserAddress(ro);
        entity.setId(id);
        if (Objects.isNull(id)) {
            validInsert(entity);
        } else {
            lambdaUpdate().eq(CommonEntity::getId, id).eq(UserAddress::getOwnerUserId, ro.getOwnerUserId()).update(entity);
        }
    }

    @Override
    public void delete(DeleteRo ro, Long ownerUserId) {
        lambdaUpdate().in(CommonEntity::getId, ro.getId()).eq(UserAddress::getOwnerUserId, ownerUserId).remove();
    }

    @Override
    public UserAddressClientComplexVo getComplex(Long id, Long ownerUserId) {
        UserAddress entity = lambdaQuery().eq(UserAddress::getId, id).eq(UserAddress::getOwnerUserId, ownerUserId).one();
        return mapping.asUserAddressClientComplexVo(entity);
    }

    @Override
    public IScroll<UserAddressClientOrdinaryVo> scroll(UserAddressClientScrollRo ro) {
        Page<UserAddress> page = lambdaQuery().eq(UserAddress::getOwnerUserId, ro.getOwnerUserId())
            .gt(CommonEntity::getId, ro.getNextId()).page(ro.ofPage());
        return PageUtils.fillScroll(page, UserAddress::getId).convert(mapping::asUserAddressClientOrdinaryVo);
    }

    @Override
    public UserAddress getDefaultByUserIdAndType(Long userId, String type) {
        return lambdaQuery().eq(UserAddress::getOwnerUserId, userId).eq(UserAddress::getType, type).eq(UserAddress::getDefaultFlag, true).one();
    }
}
