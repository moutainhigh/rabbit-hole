package in.hocg.rabbit.bmw.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [支付模块] 支付账户表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_account")
public class Account extends AbstractEntity<Account> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("所属用户")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("接入商户")
    @TableField("access_mch_id")
    private Long accessMchId;
    @ApiModelProperty("使用场景")
    @TableField("use_scenes")
    private String useScenes;
    @ApiModelProperty("账号")
    @TableField("account")
    private String account;
    @ApiModelProperty("账号(支付商户)")
    @TableField("mch_account")
    private String mchAccount;
    @ApiModelProperty("支付商户")
    @TableField("payment_mch_id")
    private Long paymentMchId;

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
