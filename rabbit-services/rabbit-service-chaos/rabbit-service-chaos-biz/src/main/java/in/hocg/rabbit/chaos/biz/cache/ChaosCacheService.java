package in.hocg.rabbit.chaos.biz.cache;

import cn.hutool.core.lang.Pair;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeDeviceType;
import in.hocg.rabbit.chaos.api.enums.VerifyCodeOptType;
import in.hocg.rabbit.chaos.api.pojo.vo.ValidVerifyCodeVo;
import org.apache.commons.lang3.tuple.Triple;

import java.time.Duration;

public interface ChaosCacheService {
    /**
     * 可否重发验证码
     *
     * @param optType  操作类型
     * @param toDevice 设备号
     * @return 是否可发送
     */
    boolean canReSendVerifyCode(VerifyCodeOptType optType, String toDevice);

    /**
     * 校验验证码
     *
     * @param serialNo   序列号
     * @param verifyCode 验证码
     * @return 是否正确验证码, 验证码信息
     */
    ValidVerifyCodeVo validVerifyCode(String serialNo, String verifyCode);

    /**
     * 获取验证码及其有效期
     *
     * @param serialNo 序列号
     * @return 验证码，有效期
     */
    Pair<String, Duration> getVerifyCode(String serialNo);

    /**
     * 获取或创建验证码(限定时间内可重用验证码)
     *
     * @param optType  操作类型
     * @param toDevice 设备号
     * @param duration 过期时间长度
     * @return 序列号,验证码,剩余过期时间
     */
    Triple<String, String, Duration> getOrCreateVerifyCode(VerifyCodeOptType optType, VerifyCodeDeviceType deviceType, String toDevice, Duration duration);

    /**
     * 创建验证码
     *
     * @param optType  操作类型
     * @param toDevice 设备号
     * @param duration 过期时间长度
     * @return 序列号,验证码,剩余过期时间
     */
    Triple<String, String, Duration> createVerifyCode(VerifyCodeOptType optType, VerifyCodeDeviceType deviceType, String toDevice, Duration duration);
}
