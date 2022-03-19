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
public class PublishedDocVo {
    @ApiModelProperty("所属用户")
    private Long ownerUserId;
    @ApiModelProperty("所属用户名")
    private String ownerUserName;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("概述")
    private String description;
}
