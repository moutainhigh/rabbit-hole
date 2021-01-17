package com.github.lotus.com.biz.pojo.ro;

import com.github.lotus.common.datadict.FeedbackType;
import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "用户反馈")
public class FeedbackInsertRo {
    @NotBlank(message = "请填写项目编号")
    @ApiModelProperty("项目编号")
    private String projectSn;
    @EnumRange(enumClass = FeedbackType.class, message = "反馈类型错误")
    @NotNull(message = "反馈类型错误")
    @ApiModelProperty("类型: issues=>问题; propose=>建议")
    private String type;
    @NotBlank(message = "请填写反馈标题")
    @ApiModelProperty("标题")
    private String subject;
    @NotBlank(message = "请填写反馈内容")
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("页面")
    private String page;
    @ApiModelProperty("联系人")
    private String contactUser;
    @ApiModelProperty("联系方式")
    private String contactInfo;
    @ApiModelProperty("扩展信息")
    private String expand;
    @ApiModelProperty("版本号")
    private String version;

    @ApiModelProperty(hidden = true)
    private String createdIp;
    @ApiModelProperty(hidden = true)
    private Long userId;
}
