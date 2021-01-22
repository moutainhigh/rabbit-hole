package com.github.lotus.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.AppCard;
import com.github.lotus.mina.biz.pojo.ro.AppCardPageRo;
import com.github.lotus.mina.biz.pojo.vo.AppComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [小程序模块] 游戏表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
public interface AppCardService extends AbstractService<AppCard> {

    IPage<AppComplexVo> paging(AppCardPageRo ro);
}
