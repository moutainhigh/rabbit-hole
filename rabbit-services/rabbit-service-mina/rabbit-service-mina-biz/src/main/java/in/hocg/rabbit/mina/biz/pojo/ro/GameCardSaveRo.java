package in.hocg.rabbit.mina.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/2/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GameCardSaveRo {
    private Long id;
    @ApiModelProperty("名称")
    private String title;
    @ApiModelProperty("LOGO")
    private String logoUrl;
    @ApiModelProperty("ROM链接")
    private String gameUrl;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("标签(暂用;分隔)")
    private String tags;
    @ApiModelProperty("排序,默认:1000")
    private Integer priority;
    @ApiModelProperty("启用状态")
    private Boolean enabled;


    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
