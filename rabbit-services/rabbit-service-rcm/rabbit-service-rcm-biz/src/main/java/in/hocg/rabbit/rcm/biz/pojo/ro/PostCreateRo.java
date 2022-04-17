package in.hocg.rabbit.rcm.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PostCreateRo {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("简介")
    private String summary;
    @ApiModelProperty("标签")
    private List<String> tags;
    @ApiModelProperty("展览图")
    private String thumbnailUrl;
    @ApiModelProperty("类目")
    private Long categoryId;
    @ApiModelProperty("附件图片")
    private List<String> files;
    @ApiModelProperty("草稿状态")
    private Boolean drafted = true;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
