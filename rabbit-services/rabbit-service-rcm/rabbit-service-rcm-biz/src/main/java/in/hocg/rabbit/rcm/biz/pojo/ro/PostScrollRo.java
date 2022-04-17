package in.hocg.rabbit.rcm.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PostScrollRo extends ScrollRo {
    @ApiModelProperty("类目")
    private String category;

    @ApiModelProperty("草稿状态")
    private Boolean drafted;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty(value = "类目", hidden = true)
    private Long categoryId;
}
