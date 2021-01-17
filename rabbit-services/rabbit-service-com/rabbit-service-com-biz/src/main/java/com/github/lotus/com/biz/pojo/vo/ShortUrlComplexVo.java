package com.github.lotus.com.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import in.hocg.boot.mybatis.plus.autoconfiguration.utils.Enabled;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/4/4.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class ShortUrlComplexVo {
    private Long id;
    @ApiModelProperty("短链码")
    private String code;
    @ApiModelProperty("原链")
    private String originalUrl;
    @ApiModelProperty("启用状态")
    private String enabled;
    @Named(idFor = "enabled", type = ChaosNamedAPI.DATA_DICT, args = {Enabled.KEY})
    private String enabledName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ApiModelProperty("创建人")
    @Named(idFor = "creator", type = ChaosNamedAPI.USERID2NICKNAME)
    private String creatorName;
}
