package com.github.lotus.chaos.biz.modules.lang.service.impl;

import com.github.lotus.chaos.biz.modules.com.entity.District;
import com.github.lotus.chaos.biz.modules.com.service.DistrictService;
import com.github.lotus.chaos.biz.modules.lang.manager.LangManager;
import com.github.lotus.chaos.biz.modules.lang.pojo.dto.IpAndAddressDto;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.SendEmailCodeRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.modules.lang.pojo.vo.IpAddressVo;
import com.github.lotus.chaos.biz.modules.lang.service.ChaosService;
import com.github.lotus.chaos.biz.modules.lang.service.EmailService;
import com.github.lotus.chaos.biz.modules.lang.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
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
public class IndexServiceImpl implements ChaosService {
    private final SmsService smsService;
    private final EmailService emailService;
    private final DistrictService districtService;
    private final StringEncryptor stringEncryptor;
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

    @Override
    public void sendEmailCode(SendEmailCodeRo ro) {
        String email = ro.getEmail();
        emailService.sendVerifyCode(email);
    }

    @Override
    public String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }
}
