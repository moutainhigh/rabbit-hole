package in.hocg.rabbit.mina.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [YouTube] YouTube频道表
 * </p>
 *
 * @author hocgin
 * @since 2022-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ytb_y2b_channel")
public class Y2bChannel extends CommonEntity<Y2bChannel> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("授权服务")
    @TableField("client_id")
    private String clientId;
    @ApiModelProperty("频道ID")
    @TableField("channel_id")
    private String channelId;
    @ApiModelProperty("频道名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("频道地址")
    @TableField("url")
    private String url;
    @ApiModelProperty("频道图片")
    @TableField("image_url")
    private String imageUrl;

}
