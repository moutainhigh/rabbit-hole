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
 * [基础模块] 短链接表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_short_url")
public class ShortUrl extends CommonEntity<ShortUrl> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("短链码")
    @TableField("code")
    private String code;
    @ApiModelProperty("原链")
    @TableField("original_url")
    private String originalUrl;


    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;

    // todo 调整
    // https://zhuanlan.zhihu.com/p/102626209
    // total_click_count：当前链接总点击次数
    // expiration_date：失效日期
    // suffix_url：链接除了域名外的后缀
    // base_url：域名

}
