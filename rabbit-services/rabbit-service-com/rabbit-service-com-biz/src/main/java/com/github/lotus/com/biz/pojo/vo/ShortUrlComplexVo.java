package com.github.lotus.com.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
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
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ApiModelProperty("创建人")
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
}
