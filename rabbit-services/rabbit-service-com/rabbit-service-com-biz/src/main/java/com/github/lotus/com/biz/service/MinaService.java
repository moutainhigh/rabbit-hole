package com.github.lotus.com.biz.service;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface MinaService {

    /**
     * 用户签到
     * - 每日只能签到一次
     *
     * @param userId 用户
     */
    void userSign(Long userId);

    /**
     * 触发观看视频
     *
     * @param userId 用户
     */
    void triggerWatchAd(Long userId);
}
