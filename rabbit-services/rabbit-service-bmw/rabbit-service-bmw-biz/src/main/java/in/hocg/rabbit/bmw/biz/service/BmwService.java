package in.hocg.rabbit.bmw.biz.service;

import in.hocg.rabbit.bmw.api.pojo.ro.GetCashierRo;
import in.hocg.rabbit.bmw.biz.pojo.ro.CloseTradeRo;
import in.hocg.rabbit.bmw.biz.pojo.ro.GoPayRo;
import in.hocg.rabbit.bmw.biz.pojo.vo.CashierInfoVo;
import in.hocg.rabbit.bmw.biz.pojo.vo.GoPayVo;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface BmwService {

    CashierInfoVo getCashierInfo(String u);

    String getCashierUrl(GetCashierRo ro);

    GoPayVo goPay(GoPayRo ro);

    void closeTrade(CloseTradeRo ro);

    String getCashierPage(String key);
}
