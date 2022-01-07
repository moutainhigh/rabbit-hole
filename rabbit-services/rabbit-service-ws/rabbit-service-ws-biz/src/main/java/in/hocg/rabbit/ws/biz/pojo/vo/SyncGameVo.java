package in.hocg.rabbit.ws.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class SyncGameVo implements Serializable {
    @ApiModelProperty("指令")
    private Object cmd;
    @ApiModelProperty("画面")
    private String screen;
}
