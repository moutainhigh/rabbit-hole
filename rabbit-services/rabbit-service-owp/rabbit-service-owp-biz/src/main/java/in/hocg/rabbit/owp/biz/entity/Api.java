package in.hocg.rabbit.owp.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

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
public class Api extends CommonEntity<Api> {

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
