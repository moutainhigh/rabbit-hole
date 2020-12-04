package com.github.lotus.chaos.biz.module.lang.service.impl;

import com.github.lotus.chaos.biz.module.com.entity.District;
import com.github.lotus.chaos.biz.module.com.service.DistrictService;
import com.github.lotus.chaos.biz.module.lang.manager.LangManager;
import com.github.lotus.chaos.biz.module.lang.pojo.dto.IpAndAddressDto;
import com.github.lotus.chaos.biz.module.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.module.lang.pojo.vo.IpAddressVo;
import com.github.lotus.chaos.biz.module.lang.service.IndexService;
import com.github.lotus.chaos.biz.module.lang.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexServiceImpl implements IndexService {
    private final SmsService smsService;
    private final DistrictService districtService;
    private final LangManager langManager;

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsService.sendSmsCode(ro);
    }

    @Override
    public IpAddressVo getAddress4ip(String ip) {
        IpAddressVo result = new IpAddressVo();
        IpAndAddressDto dto = langManager.getAddressByIp(ip);
        dto.getCityCode().ifPresent(cityCode -> {
            Optional<District> districtOpt = districtService.getCityByCityCode(cityCode);
            if (districtOpt.isPresent()) {
                District district = districtOpt.get();
                District province = districtService.getById(districtOpt.get().getParentId());
                result.setProvinceAdcode(province.getAdCode());
                result.setCityAdcode(district.getAdCode());
            }
        });
        return result.setNation(dto.getNation().orElse(null))
            .setProvince(dto.getProvince().orElse(null))
            .setCity(dto.getCity().orElse(null))
            .setIp(ip);
    }
}
