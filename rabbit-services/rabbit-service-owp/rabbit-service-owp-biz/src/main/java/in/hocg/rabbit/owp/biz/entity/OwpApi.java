package in.hocg.rabbit.owp.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [开放平台] 接口表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("owp_api")
public class OwpApi extends CommonEntity<OwpApi> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("接口编号")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("接口名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("映射服务名/域名")
    @TableField("map_uri")
    private String mapUri;
    @ApiModelProperty("映射接口地址")
    @TableField("map_path")
    private String mapPath;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Integer enabled;



}
