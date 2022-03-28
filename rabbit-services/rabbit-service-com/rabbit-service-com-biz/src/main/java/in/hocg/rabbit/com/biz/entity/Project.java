package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [通用模块] 项目表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_project")
public class Project extends CommonEntity<Project> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编码")
    @TableField("encoding")
    private String encoding;
    @TableField("config")
    private String config;
    @ApiModelProperty("图章地址")
    @TableField("logo_url")
    private String logoUrl;
    @ApiModelProperty("标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("当前版本号")
    @TableField("version")
    private String version;

}
