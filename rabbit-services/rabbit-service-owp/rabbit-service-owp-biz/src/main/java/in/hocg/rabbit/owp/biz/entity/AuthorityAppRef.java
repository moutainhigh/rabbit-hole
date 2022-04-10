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
 * [开放平台] 权限x应用表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("owp_authority_app_ref")
public class AuthorityAppRef extends CommonEntity<AuthorityAppRef> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限")
    @TableField("authority_id")
    private Long authorityId;
    @ApiModelProperty("应用")
    @TableField("app_id")
    private Long appId;



}
