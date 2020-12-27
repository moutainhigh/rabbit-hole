package com.github.lotus.chaos.biz.modules.lang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class WallpaperComplexVo {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("图片url")
    private String url;
    @ApiModelProperty("图片标题")
    private String title;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("作者信息")
    private AuthorInfoVo author;

    @Data
    @ApiModel
    public static class AuthorInfoVo {
        @ApiModelProperty("昵称")
        private String nickname;
        @ApiModelProperty("头像")
        private String avatarUrl;
        @ApiModelProperty("备注")
        private String remark;
    }
}
