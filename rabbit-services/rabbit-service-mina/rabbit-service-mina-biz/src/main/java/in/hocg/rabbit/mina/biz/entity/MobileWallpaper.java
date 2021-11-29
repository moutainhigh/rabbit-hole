package in.hocg.rabbit.mina.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [模块] 手机壁纸表
 * </p>
 *
 * @author hocgin
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mina_mobile_wallpaper")
public class MobileWallpaper extends AbstractEntity<MobileWallpaper> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("描述")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("color")
    @TableField("color")
    private String color;
    @ApiModelProperty("默认")
    @TableField("full_url")
    private String fullUrl;
    @ApiModelProperty("文件hash")
    @TableField("file_hash")
    private String fileHash;
    @ApiModelProperty("标签(暂用;分隔)")
    @TableField("tags")
    private String tags;
    @ApiModelProperty("数据来源")
    @TableField("data_source")
    private String dataSource;
    @ApiModelProperty("排序,默认:1000")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;



}
