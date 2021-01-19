package com.github.lotus.ums.biz.pojo.vo;

import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/10/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel("权限详情")
public class AuthorityComplexVo {
    private Long id;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("权限编码")
    private String encoding;
    @ApiModelProperty("权限名称")
    private String title;
    @ApiModelProperty("权限描述")
    private String remark;
}
