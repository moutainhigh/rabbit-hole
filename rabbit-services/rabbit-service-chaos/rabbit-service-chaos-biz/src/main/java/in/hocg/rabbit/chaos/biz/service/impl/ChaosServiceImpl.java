package in.hocg.rabbit.chaos.biz.service.impl;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.boot.validation.core.ValidatorUtils;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeDeviceType;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeOptType;
import in.hocg.rabbit.chaos.api.pojo.ro.SendVerifyCodeRo;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import in.hocg.rabbit.chaos.biz.cache.ChaosCacheService;
import in.hocg.rabbit.chaos.biz.constant.CacheKeys;
import in.hocg.rabbit.chaos.biz.manager.EmailManager;
import in.hocg.rabbit.chaos.biz.manager.SmsManager;
import in.hocg.rabbit.chaos.biz.pojo.dto.IpAndAddressDto;
import in.hocg.rabbit.chaos.api.pojo.vo.GetVerifyCodeVo;
import in.hocg.rabbit.chaos.biz.pojo.vo.IpAddressVo;
import in.hocg.rabbit.chaos.biz.service.ChaosService;
import in.hocg.rabbit.chaos.biz.support.lbs.LbsService;
import in.hocg.rabbit.com.api.DistrictServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import in.hocg.rabbit.common.utils.Rules;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.validation.groups.Default;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
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
public class ChaosServiceImpl implements ChaosService {
    private final SmsManager smsManager;
    private final EmailManager emailManager;
    private final DistrictServiceApi districtServiceApi;
    private final StringEncryptor stringEncryptor;
    private final LbsService lbsService;
    private final ChaosCacheService cacheService;

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
    public String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }

    @Override
    public GetVerifyCodeVo sendVerifyCode(SendVerifyCodeRo ro) {
        Optional<GetVerifyCodeVo> resultOpt = Rules.create()
            .rule(SendVerifyCodeRo.Mode.UseSms, Rules.Supplier(() -> this.sendVerifyCodeBySms(ro)))
            .rule(SendVerifyCodeRo.Mode.UseEmail, Rules.Supplier(() -> this.sendVerifyCodeByEmail(ro)))
            .of(ICode.ofThrow(ro.getMode(), SendVerifyCodeRo.Mode.class));
        return resultOpt.orElseThrow(() -> ServiceException.wrap("该方式[{}]暂不支持", ro.getMode()));
    }

    @Override
    public ValidVerifyCodeVo validVerifyCode(String serialNo, String verifyCode) {
        return cacheService.validVerifyCode(serialNo, verifyCode);
    }

    private GetVerifyCodeVo sendVerifyCodeByEmail(SendVerifyCodeRo ro) {
        SendVerifyCodeRo.Mode.UseEmailRo emailRo = ValidatorUtils.validThrow(ro.getEmailMode(), Default.class);
        VerifyCodeOptType optType = ICode.ofThrow(ro.getOptType(), VerifyCodeOptType.class);
        String email = emailRo.getEmail();
        Triple<String, String, Duration> verifyCodeState = cacheService.getOrCreateVerifyCode(optType,
            VerifyCodeDeviceType.Email, email, Duration.of(ro.getDuration(), ChronoUnit.MINUTES));
        String verifyCode = verifyCodeState.getMiddle();
        emailManager.sendVerifyCode(email, verifyCode);
        return new GetVerifyCodeVo().setSerialNo(verifyCodeState.getLeft())
            .setDuration(verifyCodeState.getRight().get(ChronoUnit.SECONDS));
    }

    private GetVerifyCodeVo sendVerifyCodeBySms(SendVerifyCodeRo ro) {
        SendVerifyCodeRo.Mode.UseSmsRo smsRo = ValidatorUtils.validThrow(ro.getSmsMode(), Default.class);
        VerifyCodeOptType optType = ICode.ofThrow(ro.getOptType(), VerifyCodeOptType.class);
        String phone = smsRo.getPhone();
        Triple<String, String, Duration> verifyCodeState = cacheService.getOrCreateVerifyCode(optType,
            VerifyCodeDeviceType.Phone, phone, Duration.of(ro.getDuration(), ChronoUnit.MINUTES));
        String verifyCode = verifyCodeState.getMiddle();
        smsManager.sendVerifyCode(phone, verifyCode);
        return new GetVerifyCodeVo().setSerialNo(verifyCodeState.getLeft())
            .setDuration(verifyCodeState.getRight().get(ChronoUnit.SECONDS));
    }

}
