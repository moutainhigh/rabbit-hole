package com.github.lotus.pay.biz.apiimpl;

import com.github.lotus.pay.api.TradeServiceApi;
import com.github.lotus.pay.api.pojo.vo.TradeOrdinaryVo;
import com.github.lotus.pay.biz.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class TradeServiceApiImpl implements TradeServiceApi {
    private final TradeService service;

    @Override
    public List<TradeOrdinaryVo> listOrdinaryById(List<Long> id) {
        return service.listOrdinaryById(id);
    }
}
