package in.hocg.rabbit.com.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.com.biz.entity.UserAddress;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientSaveRo;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientScrollRo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientOrdinaryVo;

/**
 * <p>
 * [用户模块] 物流地址表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface UserAddressService extends AbstractService<UserAddress> {

    void insertOne(UserAddressClientSaveRo ro);

    void updateOne(Long id, UserAddressClientSaveRo ro);

    void delete(DeleteRo ro, Long ownerUserId);

    UserAddressClientComplexVo getComplex(Long id, Long ownerUserId);

    IScroll<UserAddressClientOrdinaryVo> scroll(UserAddressClientScrollRo ro);

    UserAddress getDefaultByUserIdAndType(Long userId, String type);
}
