package com.github.lotus.ums.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import in.hocg.boot.named.autoconfiguration.annotation.UseNamedService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
@ApiModel("角色列表查询")
public class RoleComplexVo {
    private Long id;
    @ApiModelProperty("角色编码")
    private String encoding;
    @ApiModelProperty("角色名称")
    private String title;
    @ApiModelProperty("角色描述")
    private String remark;
    @ApiModelProperty("开启状态")
    private Boolean enabled;
    @ApiModelProperty("是否保留角色")
    private Boolean isPersist;
    @ApiModelProperty("角色使用的用户数量")
    private Integer useUserCount = 0;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ApiModelProperty("创建人")
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;

    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;

    @ApiModelProperty("权限列表")
    private List<Long> authorities = Collections.emptyList();

}
