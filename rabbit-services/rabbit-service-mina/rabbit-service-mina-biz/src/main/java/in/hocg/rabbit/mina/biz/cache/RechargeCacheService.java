package in.hocg.rabbit.mina.biz.cache;

import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RechargeCacheService {

    List<RechargeProductVo> listProduct(Long userid);
}
