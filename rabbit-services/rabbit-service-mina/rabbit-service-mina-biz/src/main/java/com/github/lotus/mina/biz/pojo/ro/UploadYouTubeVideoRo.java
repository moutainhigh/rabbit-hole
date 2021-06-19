package com.github.lotus.mina.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class UploadYouTubeVideoRo {
    @NotNull
    @ApiModelProperty("Youtube id")
    private String clientId;
    @NotNull
    @ApiModelProperty("视频来源URL")
    private String fromUrl;
    @NotNull
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("视频标签")
    private List<String> tags = Collections.emptyList();

    @ApiModelProperty("视频公开状态")
    private String privacyStatus = "public";

    @ApiModelProperty("是否修改md5")
    private Boolean isModifyMd5 = false;
}
