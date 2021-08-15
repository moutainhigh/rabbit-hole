package com.github.lotus.bmw.biz.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.lotus.bmw.api.pojo.vo.TradeSyncVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class SyncNotifyVo {
    @ApiModelProperty("任务类型")
    private String taskType;
    private LocalDateTime notifyAt;
    private String signType = "md5";
    private String sign;
    private TradeSyncVo tradeSync;
}
