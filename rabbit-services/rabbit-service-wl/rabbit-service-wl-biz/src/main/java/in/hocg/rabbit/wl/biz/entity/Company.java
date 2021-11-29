package in.hocg.rabbit.wl.biz.entity;

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
 * [物流模块] 公司表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wl_company")
public class Company extends AbstractEntity<Company> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("tel")
    @TableField("tel")
    private String tel;
    @ApiModelProperty("省区域编码")
    @TableField("province_adcode")
    private String provinceAdcode;
    @ApiModelProperty("市区区域编码")
    @TableField("city_adcode")
    private String cityAdcode;
    @ApiModelProperty("县区域编码")
    @TableField("district_adcode")
    private String districtAdcode;

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
