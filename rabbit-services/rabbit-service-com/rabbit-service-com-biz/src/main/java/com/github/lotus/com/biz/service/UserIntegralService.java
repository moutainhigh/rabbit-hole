package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.UserIntegral;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralStatsVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [通用] 用户积分表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
public interface UserIntegralService extends AbstractService<UserIntegral> {

    /**
     * 获取用户积分状态
     *
     * @param userId 用户
     * @return 积分状态
     */
    MinaIntegralStatsVo getStats(Long userId);

    /**
     * 用户积分流水
     *
     * @param ro
     * @return
     */
    IPage<MinaIntegralFlowVo> pageFlow(MinaIntegralFlowPageRo ro);
}
