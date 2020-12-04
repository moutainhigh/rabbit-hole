package com.github.lotus.wl.biz.pojo.ro.warehouse;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class WarehousePagingRo extends PageRo {
    private Long companyId;
}
