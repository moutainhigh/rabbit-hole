package in.hocg.rabbit.mina.biz.pojo.dto;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.List;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class UploadY2bDto {
    @ApiModelProperty("展示图(第一阶段)")
    private File thumbFile;
    @ApiModelProperty("标题(第一阶段)")
    private String title;
    @ApiModelProperty("描述(第一阶段)")
    private String description;
    @ApiModelProperty("视频标签(第一阶段)")
    private List<String> tags;

    @ApiModelProperty("视频公开状态(第二阶段)")
    private String privacyStatus = "private";
    @ApiModelProperty("国际化(第二阶段)")
    private String language = "ZH-CN";
    // 视频类别: https://developers-dot-devsite-v2-prod.appspot.com/youtube/v3/docs/videoCategories/list
    @ApiModelProperty("类别(第二阶段)")
    private String categoryId = "1";
    @ApiModelProperty("播放列表(第二阶段)")
    private String playlistId;
}
