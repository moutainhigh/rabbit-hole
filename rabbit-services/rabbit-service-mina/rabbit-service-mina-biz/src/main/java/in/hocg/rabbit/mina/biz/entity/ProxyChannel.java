package in.hocg.rabbit.mina.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [代理] 用户隧道表
 * </p>
 *
 * @author hocgin
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dl_proxy_channel")
public class ProxyChannel extends CommonEntity<ProxyChannel> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("隧道唯一标识")
    @TableField("channel_id")
    private String channelId;
    @ApiModelProperty("拥有人")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("隧道类型")
    @TableField("type")
    private String type;
    @ApiModelProperty("本地端口")
    @TableField("local_port")
    private Integer localPort;
    @ApiModelProperty("本地IP")
    @TableField("local_ip")
    private String localIp;
    @ApiModelProperty("域名前缀")
    @TableField("prefix")
    private String prefix;
    /**
     * 例如: forward.hocgin.top
     */
    @ApiModelProperty("域名后缀")
    @TableField("suffix")
    private String suffix;
    @ApiModelProperty("开启状态")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("租户")
    @TableField("tenant_id")
    private Long tenantId;
    @ApiModelProperty("删除状态(未删除 != 0)")
    @TableField("delete_flag")
    private Long deleteFlag;

}
