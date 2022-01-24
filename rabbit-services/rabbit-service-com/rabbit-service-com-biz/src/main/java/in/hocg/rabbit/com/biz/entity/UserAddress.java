package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [用户模块] 物流地址表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_user_address")
public class UserAddress extends LogicDeletedEntity<UserAddress> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所属用户")
    @TableField("owner_user_id")
    private Long ownerUserId;
    @ApiModelProperty("地址类型")
    @TableField("type")
    private String type;
    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty("电话")
    @TableField("tel")
    private String tel;
    @ApiModelProperty("邮编")
    @TableField("postcode")
    private String postcode;
    @ApiModelProperty("区域编码")
    @TableField("adcode")
    private String adcode;
    @ApiModelProperty("省份")
    @TableField("province")
    private String province;
    @ApiModelProperty("城市")
    @TableField("city")
    private String city;
    @ApiModelProperty("区")
    @TableField("region")
    private String region;
    @ApiModelProperty("详细地址")
    @TableField("address")
    private String address;
    @ApiModelProperty("是否默认地址")
    @TableField("default_flag")
    private Boolean defaultFlag;

}
