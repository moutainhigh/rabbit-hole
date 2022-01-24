package in.hocg.rabbit.ws.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class SyncGameRo {
    @ApiModelProperty("指令")
    private Object cmd;
    @ApiModelProperty("画面")
    private String screen;
}
