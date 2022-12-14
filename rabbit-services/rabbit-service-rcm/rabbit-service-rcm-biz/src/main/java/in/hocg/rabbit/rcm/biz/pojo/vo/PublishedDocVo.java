package in.hocg.rabbit.rcm.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
@InjectNamed
public class PublishedDocVo {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("概述")
    private String summary;
    @ApiModelProperty("关键词")
    private List<String> keyword;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("浏览次数")
    private Long viewCount;
    @ApiModelProperty("喜欢次数")
    private Long likeCount;

    @ApiModelProperty("所属用户")
    private Long ownerUserId;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2Nickname)
    private String ownerUserName;
}
