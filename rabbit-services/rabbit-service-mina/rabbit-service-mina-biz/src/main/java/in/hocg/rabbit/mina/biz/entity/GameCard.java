package in.hocg.rabbit.mina.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * [小程序模块] 游戏表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mina_game_card")
public class GameCard extends AbstractEntity<GameCard> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("LOGO")
    @TableField("logo_url")
    private String logoUrl;
    @ApiModelProperty("ROM链接")
    @TableField("game_url")
    private String gameUrl;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("标签(暂用;分隔)")
    @TableField("tags")
    private String tags;
    @ApiModelProperty("简介")
    private String bodyStr;
    @ApiModelProperty("评分")
    private BigDecimal score;
    @ApiModelProperty("热度")
    private Long heat;
    @ApiModelProperty("游戏类型")
    private String gameType;
    @ApiModelProperty("排序,默认:1000")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;



}
