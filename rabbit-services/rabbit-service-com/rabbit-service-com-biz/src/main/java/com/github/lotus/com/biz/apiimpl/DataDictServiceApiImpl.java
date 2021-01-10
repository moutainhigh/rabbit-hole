package com.github.lotus.com.biz.apiimpl;

import com.github.lotus.com.api.DataDictServiceApi;
import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.com.biz.service.DataDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DataDictServiceApiImpl implements DataDictServiceApi {
    private final DataDictService service;

    @Override
    public List<DataDictItemVo> listDataDictItemVoByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return service.listDataDictItemVoDtoByDictIdAndCode(typeCode, itemCodes);
    }
}
