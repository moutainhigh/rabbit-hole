package in.hocg.rabbit.rcm.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.rcm.api.named.RcmNamed;
import in.hocg.rabbit.rcm.api.named.RcmNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
public class PostOrdinaryVo implements Serializable {
    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("简介")
    private String summary;
    @ApiModelProperty("内容")
    private Long docTextId;
    @ApiModelProperty("标签")
    private List<String> tags;
    @ApiModelProperty("类目")
    private Long categoryId;
    @RcmNamed(idFor = "categoryId", type = RcmNamedType.CategoryName)
    private String categoryName;

    @ApiModelProperty("展览图")
    private String thumbnailUrl;
    @ApiModelProperty("观看次数")
    private Integer viewCount;
    @ApiModelProperty("喜欢次数")
    private Integer likeCount;
    @ApiModelProperty("热度指数")
    private Integer heatIdx;
    @ApiModelProperty("原文链接")
    private String originalLink;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    private Long creator;
    @ApiModelProperty("名称")
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("头像")
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2AvatarUrl)
    private String creatorAvatarUrl;

    @ApiModelProperty("最后回复人")
    private List<ReplyUser> lastReplyUsers;
    @ApiModelProperty("最后回复时间")
    private LocalDateTime lastReplyAt;
    @ApiModelProperty("回复数量")
    private Long replyCount;

    @Data
    @InjectNamed
    @ApiModel(description = "回复人")
    public static class ReplyUser implements Serializable {
        private Long replyUserId;
        @ApiModelProperty("名称")
        @ChaosNamed(idFor = "replyUserId", type = ChaosNamedType.Userid2Nickname)
        private String replyUserName;
        @ApiModelProperty("头像")
        @ChaosNamed(idFor = "replyUserId", type = ChaosNamedType.Userid2AvatarUrl)
        private String replyUserAvatarUrl;
    }
}
