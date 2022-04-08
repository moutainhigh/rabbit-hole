package in.hocg.rabbit.mina.biz.cache;

import in.hocg.rabbit.mina.biz.pojo.vo.RechargeProductVo;

import java.util.List;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RechargeCacheService {

    List<RechargeProductVo> listProduct(Long userid);
}
