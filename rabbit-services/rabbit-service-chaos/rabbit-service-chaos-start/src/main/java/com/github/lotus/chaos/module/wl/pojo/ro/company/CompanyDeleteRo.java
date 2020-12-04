package com.github.lotus.chaos.module.wl.pojo.ro.company;

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
@ApiModel("物流公司")
public class CompanyDeleteRo {
    @Size(min = 1, max = 10, message = "请选择要删除的公司")
    @ApiModelProperty("物流公司名称")
    private List<Long> id = Collections.emptyList();
}
