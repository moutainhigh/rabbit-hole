package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.AppCard;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardSaveRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaAppCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.AppCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.AppCardOrdinaryVo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaAppCardComplexVo;
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
