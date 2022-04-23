package in.hocg.rabbit.rcm.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class DraftDocVo {
    @ApiModelProperty("所属用户")
    private Long ownerUserId;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2Nickname)
    private String ownerUserName;
    @ApiModelProperty("内容对象")
    private Long contentId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("概述")
    private String summary;
    @ApiModelProperty("关键词")
    private List<String> keyword;
    @ApiModelProperty("是否草稿")
    private Boolean draft;
}
