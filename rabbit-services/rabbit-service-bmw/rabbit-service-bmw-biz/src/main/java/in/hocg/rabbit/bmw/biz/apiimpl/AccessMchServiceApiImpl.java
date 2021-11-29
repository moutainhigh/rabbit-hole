package in.hocg.rabbit.bmw.biz.apiimpl;

import in.hocg.rabbit.bmw.api.AccessMchServiceApi;
import in.hocg.rabbit.bmw.api.pojo.vo.AccessMchComplexVo;
import in.hocg.rabbit.bmw.biz.service.AccessMchService;
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
