package com.github.lotus.chaos.module.lang.service.impl;

import com.github.lotus.chaos.module.com.entity.District;
import com.github.lotus.chaos.module.com.service.DistrictService;
import com.github.lotus.chaos.module.lang.manager.LangManager;
import com.github.lotus.chaos.module.lang.pojo.dto.IpAndAddressDto;
import com.github.lotus.chaos.module.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.module.lang.pojo.vo.IpAddressVo;
import com.github.lotus.chaos.module.lang.service.IndexService;
import com.github.lotus.chaos.module.lang.service.SmsService;
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
                result.setAdCode(district.getAdCode());
                result.setTitle(district.getTitle());
            }
        });
        return result.setNation(dto.getNation().orElse(null))
            .setProvince(dto.getProvince().orElse(null))
            .setCity(dto.getCity().orElse(null))
            .setIp(ip);
    }
}
