package in.hocg.rabbit.chaos.biz.service.impl;

import in.hocg.rabbit.chaos.biz.constant.CacheKeys;
import in.hocg.rabbit.chaos.biz.pojo.dto.IpAndAddressDto;
import in.hocg.rabbit.chaos.biz.pojo.ro.SendEmailCodeRo;
import in.hocg.rabbit.chaos.biz.pojo.ro.SendSmsCodeRo;
import in.hocg.rabbit.chaos.biz.pojo.vo.IpAddressVo;
import in.hocg.rabbit.chaos.biz.service.ChaosService;
import in.hocg.rabbit.chaos.biz.support.email.EmailService;
import in.hocg.rabbit.chaos.biz.support.lbs.LbsService;
import in.hocg.rabbit.chaos.biz.support.sms.SmsService;
import in.hocg.rabbit.com.api.DistrictServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.cache.annotation.Cacheable;
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
    public Long sendSmsCode(SendSmsCodeRo ro) {
        return smsService.sendSmsCode(ro);
    }

    @Override
    @Cacheable(cacheNames = CacheKeys.GET_ADDRESS_BY_IP, key = "#ip", unless = "#result == null")
    public IpAddressVo getAddress4ip(String ip) {
        log.info("正在查询IP:[{}]的物理地址", ip);
        IpAddressVo result = new IpAddressVo();
        IpAndAddressDto dto = lbsService.getAddressByIp(ip);
        dto.getCityCode().ifPresent(cityCode -> {
            DistrictComplexVo district = districtServiceApi.getComplexByCityCode(cityCode);
            if (Objects.nonNull(district)) {
                DistrictComplexVo province = districtServiceApi.getComplexById(district.getParentId());
                result.setProvinceAdcode(province.getAdcode());
                result.setCityAdcode(district.getAdcode());
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
