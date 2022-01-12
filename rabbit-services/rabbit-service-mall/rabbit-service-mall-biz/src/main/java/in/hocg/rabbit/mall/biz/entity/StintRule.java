package in.hocg.rabbit.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [促销模块] 限制规则表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mkt_stint_rule")
public class StintRule extends LogicDeletedEntity<StintRule> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("规则名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("限制类型")
    @TableField("type")
    private String type;
    @ApiModelProperty("规则")
    @TableField("rule")
    private String rule;

}
