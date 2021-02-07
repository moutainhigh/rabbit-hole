package com.github.lotus.pay.biz.apiimpl;

import com.github.lotus.pay.api.AccessAppServiceApi;
import com.github.lotus.pay.api.pojo.vo.AccessAppOrdinaryVo;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.service.AccessAppService;
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
public class AccessAppServiceApiImpl implements AccessAppServiceApi {
    private final AccessAppService service;

    @Override
    public List<AccessAppOrdinaryVo> listOrdinaryById(List<Long> id) {
        return service.listOrdinaryById(id);
    }
}
