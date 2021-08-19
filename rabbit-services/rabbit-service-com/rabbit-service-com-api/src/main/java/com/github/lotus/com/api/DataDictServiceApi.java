package com.github.lotus.com.api;

import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.common.constant.GlobalConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface DataDictServiceApi {
    String CONTEXT_ID = "DataDictServiceApi";

    @PostMapping(value = CONTEXT_ID + "/listDataDictItemVoByDictIdAndCode", headers = GlobalConstant.FEIGN_HEADER)
    List<DataDictItemVo> listDataDictItemVoByDictIdAndCode(@RequestParam("typeCode") String typeCode, @RequestParam("itemCodes") List<String> itemCodes);
}
