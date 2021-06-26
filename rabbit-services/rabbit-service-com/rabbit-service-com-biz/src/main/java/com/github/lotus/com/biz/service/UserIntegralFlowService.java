package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.UserIntegralFlow;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.time.LocalDate;

/**
 * <p>
 * [通用] 用户积分流水表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
public interface UserIntegralFlowService extends AbstractService<UserIntegralFlow> {

    IPage<MinaIntegralFlowVo> pageFlow(MinaIntegralFlowPageRo ro);

    /**
     * 判断指定日期有过签到
     *
     * @param userId
     * @param now
     * @return
     */
    Integer countSignByDate(Long userId, LocalDate now);

    /**
     * 积分视频指定日期观看次数
     *
     * @param userId
     * @param now
     * @return
     */
    Integer countWatchAdByDate(Long userId, LocalDate now);
}
