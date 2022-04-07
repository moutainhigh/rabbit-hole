package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.mina.api.pojo.ro.QueryRechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeProductVo;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeOrderPageRo;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeProductCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeAccountVo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderOrdinaryVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [功能模块] 充值单据表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
public interface RechargeOrderService extends AbstractService<RechargeOrder> {

    RechargeOrderVo recharge(RechargeOrderRo ro);

    List<RechargeProductVo> listProduct(Long userId);

    RechargeOrderVo queryRecharge(QueryRechargeOrderRo ro);

    List<RechargeOrder> listByExecutingAndCreatedAt(LocalDateTime startAt, LocalDateTime endAt);

    List<RechargeOrder> listByWaitNotify(LocalDateTime startAt, LocalDateTime endAt);

    void rechargeNotify(String orderNo);

    void notifyAccess(Long id, Long userId);

    void notifyAccess(RechargeOrder entity);

    void tryHandleRechargeResult(RechargeOrder entity);

    void notifyResult(Long id, Boolean isOk, Integer nowCount, String notifyResult, LocalDateTime nextNotifyAt);

    IPage<RechargeOrderOrdinaryVo> paging(RechargeOrderPageRo ro);

    RechargeAccountVo getAccount(Long userId);

    List<KeyValue> completeWithProduct(RechargeProductCompleteRo ro);
}
