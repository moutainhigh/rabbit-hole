package in.hocg.rabbit.mina.biz.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [功能模块] 充值账户表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mina_recharge_account")
public class RechargeAccount extends CommonEntity<RechargeAccount> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所属用户")
    @TableField("owner_user_id")
    private Long ownerUserId;
    @ApiModelProperty("手续费率(收手续费)")
    @TableField("free_rate")
    private BigDecimal freeRate;
    @ApiModelProperty("apikey")
    @TableField("apikey")
    private String apikey;
    @ApiModelProperty("可用额度(元)")
    @TableField("avail_amt")
    private BigDecimal availAmt;


}
