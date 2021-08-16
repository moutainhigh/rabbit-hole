package com.github.lotus.bmw.biz.cache;

import com.github.lotus.bmw.biz.entity.AccessMch;
import com.github.lotus.bmw.biz.pojo.dto.CashierInfoDto;

import java.util.Optional;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface BmwCacheService {

    AccessMch getAccessMchByEncoding(String encoding);

    /**
     * 获取收银台信息
     *
     * @param u
     * @return
     */
    Optional<CashierInfoDto> getCashierInfo(String u);

    /**
     * 设置收银台信息
     *
     * @param cashierInfo
     * @return
     */
    String setCashierInfo(CashierInfoDto cashierInfo);
}
