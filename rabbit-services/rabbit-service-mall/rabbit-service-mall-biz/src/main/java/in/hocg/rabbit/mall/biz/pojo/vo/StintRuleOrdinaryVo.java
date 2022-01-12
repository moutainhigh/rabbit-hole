package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class StintRuleOrdinaryVo {
    @ApiModelProperty("规则名称")
    private String title;
    @ApiModelProperty("限制类型")
    private String type;
}
