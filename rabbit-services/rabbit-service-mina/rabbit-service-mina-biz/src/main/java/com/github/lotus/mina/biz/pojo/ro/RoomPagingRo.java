package com.github.lotus.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class RoomPagingRo extends PageRo {
    @ApiModelProperty
    private String keyword;
}
