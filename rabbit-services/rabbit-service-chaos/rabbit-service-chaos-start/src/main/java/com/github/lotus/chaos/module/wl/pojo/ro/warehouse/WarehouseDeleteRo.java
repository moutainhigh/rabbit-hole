package com.github.lotus.chaos.module.wl.pojo.ro.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("物流仓库")
public class WarehouseDeleteRo {
    @Size(min = 1, max = 10, message = "请选择要删除的仓库")
    @ApiModelProperty("物流仓库")
    private List<Long> id = Collections.emptyList();
}
