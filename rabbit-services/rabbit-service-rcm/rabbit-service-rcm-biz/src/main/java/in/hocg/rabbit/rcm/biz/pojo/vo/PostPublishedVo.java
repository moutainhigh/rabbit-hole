package in.hocg.rabbit.rcm.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.rcm.api.named.RcmNamed;
import in.hocg.rabbit.rcm.api.named.RcmNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2022/4/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class PostPublishedVo implements Serializable {
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("概述")
    private String summary;
    @ApiModelProperty("关键词")
    private List<String> tags;
    @ApiModelProperty("类目")
    private Long categoryId;
    @RcmNamed(idFor = "categoryId", type = RcmNamedType.CategoryName)
    private String categoryName;
    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("展览图")
    private String thumbnailUrl;
    @ApiModelProperty("原文链接")
    private String originalLink;

    @ApiModelProperty("浏览次数")
    private Long viewCount;
    @ApiModelProperty("喜欢次数")
    private Long likeCount;

    private LocalDateTime lastUpdatedAt;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    private Long creator;
    @ApiModelProperty("名称")
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("头像")
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2AvatarUrl)
    private String creatorAvatarUrl;
}
