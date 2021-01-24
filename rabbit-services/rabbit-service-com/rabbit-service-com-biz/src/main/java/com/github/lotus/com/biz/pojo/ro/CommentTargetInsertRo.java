package com.github.lotus.com.biz.pojo.ro;

import com.github.lotus.common.datadict.CommentTargetType;

import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class CommentTargetInsertRo {
    @ApiModelProperty("评论对象ID")
    private Long relId;
    @EnumRange(enumClass = CommentTargetType.class, message = "评论对象类型错误")
    @ApiModelProperty("评论对象类型")
    private String relType;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
}
