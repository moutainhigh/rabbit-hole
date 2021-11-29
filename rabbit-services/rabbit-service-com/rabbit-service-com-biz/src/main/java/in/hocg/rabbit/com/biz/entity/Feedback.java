package in.hocg.rabbit.com.biz.entity;

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
 * [通用模块] 用户反馈表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_feedback")
public class Feedback extends AbstractEntity<Feedback> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("项目")
    @TableField("project_id")
    private Long projectId;
    @ApiModelProperty("类型: issues=>问题; propose=>建议")
    @TableField("type")
    private String type;
    @ApiModelProperty("标题")
    @TableField("subject")
    private String subject;
    @ApiModelProperty("内容")
    @TableField("content")
    private String content;
    @ApiModelProperty("页面")
    @TableField("page")
    private String page;
    @ApiModelProperty("联系人")
    @TableField("contact_user")
    private String contactUser;
    @ApiModelProperty("联系方式")
    @TableField("contact_info")
    private String contactInfo;
    @ApiModelProperty("版本号")
    @TableField("version")
    private String version;
    @ApiModelProperty("扩展信息")
    @TableField("expand")
    private String expand;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者IP")
    @TableField("created_ip")
    private String createdIp;
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;



}
