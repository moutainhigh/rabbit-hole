package in.hocg.rabbit.com.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedServiceApi;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class DataDictComplexVo {
    private Long id;
    private String title;
    private String code;
    private String remark;
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("最后更新时间")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;

    private List<DataDictItemOrdinaryVo> items = Collections.emptyList();
}
