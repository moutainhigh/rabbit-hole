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
 * [开放平台] 开发者应用表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("owp_developer_app")
public class DeveloperApp extends CommonEntity<DeveloperApp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("应用名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("应用编号")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("应用密钥")
    @TableField("secret_key")
    private String secretKey;
    @ApiModelProperty("开发者")
    @TableField("developer_user_id")
    private Long developerUserId;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;



}
