package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * [基础模块] 城市规划表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_district")
public class District extends TreeEntity<District> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty("树路径，组成方式: /父路径/当前ID")
    @TableField("tree_path")
    private String treePath;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("区域编码")
    @TableField("adcode")
    private String adcode;
    @ApiModelProperty("城市编码")
    @TableField("city_code")
    private String cityCode;
    @ApiModelProperty("城市规划级别")
    @TableField("level")
    private String level;
    @ApiModelProperty("中心(纬度)")
    @TableField("lat")
    private BigDecimal lat;
    @ApiModelProperty("中心(经度)")
    @TableField("lng")
    private BigDecimal lng;
    @ApiModelProperty("名称")
    @TableField("title")
    private String title;

}
