package com.github.lotus.ums.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * [用户模块] 收货地址表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ums_user_address")
public class UserAddress extends AbstractEntity<UserAddress> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("ID")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("收件人姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty("收件人手机号")
    @TableField("phone")
    private String phone;
    @ApiModelProperty("邮政编码")
    @TableField("post_code")
    private String postCode;
    @ApiModelProperty("省份")
    @TableField("province")
    private String province;
    @ApiModelProperty("城市")
    @TableField("city")
    private String city;
    @ApiModelProperty("区")
    @TableField("region")
    private String region;
    @ApiModelProperty("详细地址(街道)")
    @TableField("address")
    private String address;
    @ApiModelProperty("区域编码")
    @TableField("adcode")
    private String adcode;
    @TableField("default_flag")
    private Boolean defaultFlag;



}
