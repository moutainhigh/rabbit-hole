package in.hocg.rabbit.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.listeners.EntityListeners;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.rabbit.mall.biz.entity.valid.ShopValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [商品模块] 店铺表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_shop")
@EntityListeners(ShopValid.class)
public class Shop extends CommonEntity<Shop> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("店铺名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

}
