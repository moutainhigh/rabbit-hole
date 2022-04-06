package in.hocg.rabbit.mina.biz.docking.recharge;

import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.CheckRechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.RechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.CheckRechargeVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.RechargeVo;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RechargeDockingService {

    RechargeVo recharge(RechargeRo ro);

    CheckRechargeVo checkRecharge(CheckRechargeRo ro);
}
