package com.github.lotus.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.mina.biz.entity.AppCard;
import com.github.lotus.mina.biz.pojo.ro.AppCardCompleteRo;
import com.github.lotus.mina.biz.pojo.ro.AppCardPagingRo;
import com.github.lotus.mina.biz.pojo.ro.AppCardSaveRo;
import com.github.lotus.mina.biz.pojo.ro.MinaAppCardPagingRo;
import com.github.lotus.mina.biz.pojo.vo.AppCardComplexVo;
import com.github.lotus.mina.biz.pojo.vo.AppCardOrdinaryVo;
import com.github.lotus.mina.biz.pojo.vo.MinaAppCardComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [小程序模块] 游戏表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
public interface AppCardService extends AbstractService<AppCard> {

    IPage<MinaAppCardComplexVo> pagingForMina(MinaAppCardPagingRo ro);

    IPage<AppCardOrdinaryVo> paging(AppCardPagingRo ro);

    List<AppCardOrdinaryVo> complete(AppCardCompleteRo ro);

    void insertOne(AppCardSaveRo ro);

    void updateOne(Long id, AppCardSaveRo ro);

    AppCardComplexVo getComplex(Long id);

    void deleteOne(Long id);
}
