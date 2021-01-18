package com.github.lotus.chaos.biz.service.impl;

import com.github.lotus.chaos.biz.pojo.dto.IpAndAddressDto;
import com.github.lotus.chaos.biz.pojo.ro.SendEmailCodeRo;
import com.github.lotus.chaos.biz.pojo.ro.SendSmsCodeRo;
import com.github.lotus.chaos.biz.pojo.vo.IpAddressVo;
import com.github.lotus.chaos.biz.service.ChaosService;
import com.github.lotus.chaos.biz.support.email.EmailService;
import com.github.lotus.chaos.biz.support.lbs.LbsService;
import com.github.lotus.chaos.biz.support.sms.SmsService;
import com.github.lotus.com.api.DistrictServiceApi;
import com.github.lotus.com.api.pojo.vo.DistrictComplexVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ChaosServiceImpl implements ChaosService {
    private final SmsService smsService;
    private final EmailService emailService;
    private final DistrictServiceApi districtServiceApi;
    private final StringEncryptor stringEncryptor;
    private final LbsService lbsService;

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsService.sendSmsCode(ro);
    }

    @Override
    public IpAddressVo getAddress4ip(String ip) {
        log.info("正在查询IP:[{}]的物理地址", ip);
        IpAddressVo result = new IpAddressVo();
        IpAndAddressDto dto = lbsService.getAddressByIp(ip);
        dto.getCityCode().ifPresent(cityCode -> {
            DistrictComplexVo district = districtServiceApi.getComplexByCityCode(cityCode);
            if (Objects.nonNull(district)) {
                DistrictComplexVo province = districtServiceApi.getComplexById(district.getParentId());
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
