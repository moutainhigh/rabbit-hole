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
 * [权限模块] 用户组x用户表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_user_group_user_ref")
public class UserGroupUserRef extends AbstractEntity<UserGroupUserRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("用户组ID")
    @TableField("user_group_id")
    private Long userGroupId;
    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;



}
