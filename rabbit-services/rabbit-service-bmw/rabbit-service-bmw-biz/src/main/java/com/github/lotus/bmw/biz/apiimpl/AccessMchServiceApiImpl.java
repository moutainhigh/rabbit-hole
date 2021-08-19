package com.github.lotus.bmw.biz.apiimpl;

import com.github.lotus.bmw.api.AccessMchServiceApi;
import com.github.lotus.bmw.api.pojo.vo.AccessMchComplexVo;
import com.github.lotus.bmw.biz.service.AccessMchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by hocgin on 2021/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class AccessMchServiceApiImpl implements AccessMchServiceApi {
    private final AccessMchService service;

    @Override
    public List<AccessMchComplexVo> listComplexById(Collection<Long> id) {
        return service.listComplexById(id);
    }
}
