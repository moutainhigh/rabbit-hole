package com.github.lotus.ums.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.ums.biz.enumns.Gender;
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
 * Created by hocgin on 2020/12/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class AccountComplexVo {
    private Long id;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("开启状态")
    private Boolean enabled;
    @ApiModelProperty("性别(0:女, 1:男)")
    private Integer gender;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "gender", type = NamedType.DataDict,
        args = {Gender.KEY})
    private String genderName;

    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;

    @ApiModelProperty("已绑定的社交登陆类型")
    private List<SocialItem> social = Collections.emptyList();
    @ApiModelProperty("角色")
    private List<Long> roles = Collections.emptyList();


    @Data
    @Accessors(chain = true)
    @ApiModel(description = "社交登陆")
    public static class SocialItem {
        @ApiModelProperty("社交登陆类型")
        private String socialType;
        private String socialTypeName;
    }
}
