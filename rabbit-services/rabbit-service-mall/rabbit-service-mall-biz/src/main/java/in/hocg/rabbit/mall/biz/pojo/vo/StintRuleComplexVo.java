package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class StintRuleComplexVo {
    @ApiModelProperty("规则名称")
    private String title;
    @ApiModelProperty("限制类型")
    private String type;
    @ApiModelProperty("规则")
    private String rule;
}
