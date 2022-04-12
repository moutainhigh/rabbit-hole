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
 * [开放平台] 权限表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("owp_authority")
public class OwpAuthority extends CommonEntity<OwpAuthority> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Integer enabled;



}
