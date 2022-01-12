package in.hocg.rabbit.mall.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class StintRuleComplexVo {
    @ApiModelProperty("规则名称")
    private String title;
    @ApiModelProperty("限制类型")
    private String type;
    @ApiModelProperty("规则")
    private String rule;
}
