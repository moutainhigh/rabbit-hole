package in.hocg.rabbit.rcm.api.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
    private String ownerUserName;
    @ApiModelProperty("内容对象")
    private Long contentId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("概述")
    private String summary;
    @ApiModelProperty("是否草稿")
    private Boolean draft;
}
