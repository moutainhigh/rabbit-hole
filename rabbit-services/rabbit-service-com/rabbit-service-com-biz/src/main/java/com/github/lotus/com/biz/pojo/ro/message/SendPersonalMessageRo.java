package com.github.lotus.com.biz.pojo.ro.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class SendPersonalMessageRo {
    @ApiModelProperty(value = "内容", required = true)
    private String content;
    @ApiModelProperty(value = "接收人", required = true)
    private Long receiver;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
