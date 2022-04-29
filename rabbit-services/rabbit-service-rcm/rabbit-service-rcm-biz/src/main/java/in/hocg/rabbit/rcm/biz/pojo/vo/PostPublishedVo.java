package in.hocg.rabbit.rcm.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
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
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("概述")
    private String summary;
    @ApiModelProperty("关键词")
    private List<String> tags;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("浏览次数")
    private Long viewCount;
    @ApiModelProperty("喜欢次数")
    private Long likeCount;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    @ApiModelProperty("所属用户")
    private Long ownerUserId;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2Nickname)
    private String ownerUserName;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2AvatarUrl)
    private String ownerUserAvatarUrl;
}
