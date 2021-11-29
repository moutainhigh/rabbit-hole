package in.hocg.rabbit.com.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class ProjectSaveRo {
    @ApiModelProperty("编码")
    private String encoding;
    @ApiModelProperty("图章地址")
    private String logoUrl;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("当前版本号")
    private String version;

    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
