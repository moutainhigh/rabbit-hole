package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.GameCard;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.ro.GameCardSaveRo;
import in.hocg.rabbit.mina.biz.pojo.ro.MinaGameCardPagingRo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.GameCardOrdinaryVo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaGameCardComplexVo;
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
public interface GameCardService extends AbstractService<GameCard> {

    IPage<MinaGameCardComplexVo> pagingForMina(MinaGameCardPagingRo ro);

    IPage<GameCardOrdinaryVo> paging(GameCardPagingRo ro);

    List<GameCardOrdinaryVo> complete(GameCardCompleteRo ro);

    void insertOne(GameCardSaveRo ro);

    void updateOne(Long id, GameCardSaveRo ro);

    GameCardComplexVo getComplex(Long id);

    GameCardComplexVo getComplexWithMina(Long id);

    void deleteOne(Long id);
}
