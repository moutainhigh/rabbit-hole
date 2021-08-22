package com.github.lotus.com.biz.apiimpl;

import com.github.lotus.com.api.SnCodeServiceApi;
import com.github.lotus.com.biz.service.impl.SnCodeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SnCodeServiceApiImpl implements SnCodeServiceApi {
    private final SnCodeServiceImpl snCodeService;

    @Override
    public String getSnCode(String groupCode) {
        return snCodeService.getSnCode(groupCode);
    }
}
