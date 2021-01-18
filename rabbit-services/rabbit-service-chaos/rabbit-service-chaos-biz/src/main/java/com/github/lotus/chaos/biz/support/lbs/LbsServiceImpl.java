package com.github.lotus.chaos.biz.support.lbs;

import com.github.lotus.chaos.api.pojo.vo.AMapDistrictVo;
import com.github.lotus.chaos.biz.constant.ChaosCacheKeys;
import com.github.lotus.chaos.biz.manager.LbsManager;
import com.github.lotus.chaos.biz.pojo.dto.IpAndAddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LbsServiceImpl implements LbsService {
    private final LbsManager lbsManager;

    @Override
    public List<AMapDistrictVo> listDistrict() {
        return lbsManager.listDistrict();
    }

    @Override
    @Cacheable(cacheNames = ChaosCacheKeys.GET_ADDRESS_BY_IP, key = "#ip")
    public IpAndAddressDto getAddressByIp(String ip) {
        return lbsManager.getAddressByIp(ip);
    }
}
