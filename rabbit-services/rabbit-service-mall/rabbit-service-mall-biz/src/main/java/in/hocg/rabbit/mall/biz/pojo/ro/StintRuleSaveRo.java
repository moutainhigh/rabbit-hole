package in.hocg.rabbit.mall.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.boot.validation.annotation.Json;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.rabbit.mall.api.enums.rule.StintRuleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class StintRuleSaveRo {
    @NotNull(groups = {Insert.class}, message = "规则名称不能为空")
    @ApiModelProperty("规则名称")
    private String title;
    @EnumRange(groups = {Update.class, Insert.class}, enumClass = StintRuleType.class, message = "规则类型不合法")
    @NotNull(groups = {Insert.class}, message = "限制类型不能为空")
    @ApiModelProperty("限制类型")
    private String type;
    @NotNull(groups = {Insert.class}, message = "规则不能为空")
    @Json(groups = {Update.class, Insert.class}, message = "规则错误")
    @ApiModelProperty("规则")
    private String rule;
}
