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
@TableName("mina_app_card")
public class AppCard extends AbstractEntity<AppCard> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("LOGO")
    @TableField("logo_url")
    private String logoUrl;
    @ApiModelProperty("小程序链接")
    @TableField("page_url")
    private String pageUrl;
    @ApiModelProperty("小程序appid")
    @TableField("appid")
    private String appid;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("标签(暂用;分隔)")
    @TableField("tags")
    private String tags;
    @ApiModelProperty("排序,默认:1000")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("置顶状态")
    @TableField("is_top")
    private Boolean isTop;

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
