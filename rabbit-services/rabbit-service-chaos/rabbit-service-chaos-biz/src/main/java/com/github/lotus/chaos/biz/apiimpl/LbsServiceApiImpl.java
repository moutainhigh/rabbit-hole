package com.github.lotus.chaos.biz.apiimpl;

import com.github.lotus.chaos.api.LbsServiceApi;
import com.github.lotus.chaos.api.pojo.vo.AMapDistrictVo;
import com.github.lotus.chaos.biz.support.lbs.LbsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class LbsServiceApiImpl implements LbsServiceApi {
    private final LbsService service;

    @Override
    public List<AMapDistrictVo> listDistrict() {
        return service.listDistrict();
    }
}
