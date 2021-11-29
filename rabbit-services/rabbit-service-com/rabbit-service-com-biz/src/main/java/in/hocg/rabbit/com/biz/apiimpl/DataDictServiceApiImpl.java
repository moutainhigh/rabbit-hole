package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.DataDictServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.DataDictItemVo;
import in.hocg.rabbit.com.biz.service.DataDictService;
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
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DataDictServiceApiImpl implements DataDictServiceApi {
    private final DataDictService service;

    @Override
    public List<DataDictItemVo> listDataDictItemVoByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return service.listDataDictItemVoDtoByDictIdAndCode(typeCode, itemCodes);
    }
}
