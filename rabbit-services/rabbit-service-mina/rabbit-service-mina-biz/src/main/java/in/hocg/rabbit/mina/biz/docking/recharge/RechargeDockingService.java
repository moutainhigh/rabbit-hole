package in.hocg.rabbit.mina.biz.docking.recharge;

import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.CheckRechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.QueryElecityRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.QueryProductRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.RechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.CheckRechargeVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ElecityVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ProductVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.RechargeVo;

import java.util.List;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RechargeDockingService {

    RechargeVo recharge(RechargeRo ro);

    List<CheckRechargeVo> checkRecharge(CheckRechargeRo ro);

    List<ProductVo> listProduct(QueryProductRo ro);

    List<ElecityVo> listElecity(QueryElecityRo ro);

}
