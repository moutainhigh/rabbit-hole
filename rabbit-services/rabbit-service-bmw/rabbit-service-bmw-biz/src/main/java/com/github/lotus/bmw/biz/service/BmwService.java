package com.github.lotus.bmw.biz.service;

import com.github.lotus.bmw.api.pojo.ro.GetCashierRo;
import com.github.lotus.bmw.biz.pojo.dto.CashierInfoDto;
import com.github.lotus.bmw.biz.pojo.ro.CloseTradeRo;
import com.github.lotus.bmw.biz.pojo.ro.GoPayRo;
import com.github.lotus.bmw.biz.pojo.vo.CashierInfoVo;
import com.github.lotus.bmw.biz.pojo.vo.GoPayVo;

import java.util.Optional;

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
