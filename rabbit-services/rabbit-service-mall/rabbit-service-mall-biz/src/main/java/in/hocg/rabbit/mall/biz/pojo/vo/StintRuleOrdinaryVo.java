package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
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
@InjectNamed
@Accessors(chain = true)
public class StintRuleOrdinaryVo {
    private Long id;
    @ApiModelProperty("规则名称")
    private String title;
    @ApiModelProperty("限制类型")
    private String type;
    @MallNamed(idFor = "type", type = MallNamedType.DataDictName, args = {MallDataDictKeys.STINT_RULE_TYPE})
    private String typeName;
}
