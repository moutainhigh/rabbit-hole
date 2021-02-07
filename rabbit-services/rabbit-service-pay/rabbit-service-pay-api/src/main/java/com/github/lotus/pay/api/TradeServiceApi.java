package com.github.lotus.pay.api;

import com.github.lotus.pay.api.pojo.vo.TradeOrdinaryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = TradeServiceApi.CONTEXT_ID)
public interface TradeServiceApi {
    String CONTEXT_ID = "TradeServiceApi";

    @PostMapping(CONTEXT_ID + "/listOrdinaryById")
    List<TradeOrdinaryVo> listOrdinaryById(@RequestParam("id") List<Long> id);
}
