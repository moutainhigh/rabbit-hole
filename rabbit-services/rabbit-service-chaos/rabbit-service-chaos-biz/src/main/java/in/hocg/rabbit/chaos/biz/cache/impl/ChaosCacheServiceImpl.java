package in.hocg.rabbit.chaos.biz.cache.impl;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import in.hocg.boot.cache.autoconfiguration.repository.CacheRepository;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeDeviceType;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeOptType;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import in.hocg.rabbit.chaos.biz.cache.ChaosCacheService;
import in.hocg.rabbit.chaos.biz.constant.CacheKeys;
import in.hocg.rabbit.chaos.biz.constant.ChaosConstants;
import in.hocg.rabbit.chaos.biz.pojo.dto.VerifyCodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ChaosCacheServiceImpl implements ChaosCacheService {
    private final CacheRepository repository;


    @Override
    public boolean canReSendVerifyCode(VerifyCodeOptType optType, String toDevice) {
        String serialNo = CacheKeys.getVerifyCodeKey(optType.getCodeStr(), toDevice);
        if (!repository.exists(serialNo)) {
            return true;
        }
        Long expire = ObjectUtil.defaultIfNull(repository.getExpire(serialNo, TimeUnit.SECONDS), 0L);
        return expire < ChaosConstants.LIMIT_REUSE_EXPIRED;
    }

    @Override
    public ValidVerifyCodeVo validVerifyCode(String serialNo, String verifyCode) {
        ValidVerifyCodeVo result = new ValidVerifyCodeVo();
        VerifyCodeDto value = repository.get(serialNo);
        boolean isSuccess = Objects.nonNull(value) && StrUtil.equals(value.getVerifyCode(), verifyCode);
        if (isSuccess) {
            result.setVerifyCode(value.getVerifyCode());
            result.setOptType(value.getOptType());
            result.setDeviceType(value.getDeviceType());
            result.setToDevice(value.getToDevice());
            repository.del(serialNo);
        }
        return result.setSuccess(isSuccess);
    }

    @Override
    public Pair<String, Duration> getVerifyCode(String serialNo) {
        VerifyCodeDto value = repository.get(serialNo);
        if (Objects.isNull(value)) {
            return new Pair<>(null, Duration.ZERO);
        }
        Long expire = repository.getExpire(serialNo, TimeUnit.SECONDS);
        return new Pair<>(value.getVerifyCode(), Duration.of(ObjectUtil.defaultIfNull(expire, 0L), ChronoUnit.SECONDS));
    }

    @Override
    public Triple<String, String, Duration> getOrCreateVerifyCode(VerifyCodeOptType optType, VerifyCodeDeviceType deviceType,
                                                                  String toDevice, Duration duration) {
        ValidUtils.isTrue(this.canReSendVerifyCode(optType, toDevice), "验证码已发送,请勿重复点击");
        String serialNo = CacheKeys.getVerifyCodeKey(optType.getCodeStr(), toDevice);
        Pair<String, Duration> cacheState = getVerifyCode(serialNo);
        Duration expired = cacheState.getValue();
        if (expired.get(ChronoUnit.SECONDS) > ChaosConstants.LIMIT_REUSE_EXPIRED) {
            String verifyCode = cacheState.getKey();
            log.debug("获取旧验证码[操作类型: {}, 设备: {}, 验证码: {}, 序列号: {}]", optType, toDevice, verifyCode, serialNo);
            return Triple.of(serialNo, verifyCode, expired);
        }
        return createVerifyCode(optType, deviceType, toDevice, duration);
    }

    @Override
    public Triple<String, String, Duration> createVerifyCode(VerifyCodeOptType optType, VerifyCodeDeviceType deviceType,
                                                             String toDevice, Duration duration) {
        String serialNo = CacheKeys.getVerifyCodeKey(optType.getCodeStr(), toDevice);
        String verifyCode = RandomUtil.randomNumbers(4);
        VerifyCodeDto verifyCodeDto = new VerifyCodeDto();
        verifyCodeDto.setVerifyCode(verifyCode);
        verifyCodeDto.setOptType(optType.getCodeStr());
        verifyCodeDto.setDeviceType(deviceType.getCodeStr());
        verifyCodeDto.setToDevice(toDevice);
        repository.setExpire(serialNo, verifyCodeDto, duration);
        log.debug("创建新验证码[操作类型: {}, 设备: {}, 验证码: {}, 序列号: {}]", optType, toDevice, verifyCode, serialNo);
        return Triple.of(serialNo, verifyCode, duration);
    }


}
